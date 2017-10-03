package com.loic;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    private static final String OUTPUT_NAME = "Output.xlsx";
    private static final String PRODUCT_CONSEIL = "Extractions Produit Copernic Conseil 20170901.xlsx";
    private static final String VALUE_CONSEIL = "Extractions Valeur Copernic Conseil 20170901.xlsx";

    private Map<FamilyProduct, Integer> codeMap = new HashMap<>();
    private Map<Integer, Set<String>> codeIsinMap = new HashMap<>();
    private Map<String, Support> supportMap = new HashMap<>();

    private Map<String, Integer> oneSupportMap = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        try {
            if (args.length == 0) {
                main.preProcess(PRODUCT_CONSEIL, VALUE_CONSEIL);
                main.process(OUTPUT_NAME);
            } else if (args.length == 3) {
                main.preProcess(args[0], args[1]);
                main.process(args[2]);
            } else {
                System.out.println("Please give PRODUCT_CONSEIL, VALUE_CONSEIL, OUTPUT file name");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Note risk processe V3...");
    }

    private void preProcess(String produitConseil, String valeurConseil) throws IOException {
        InputStream pc = new FileInputStream(produitConseil);
        InputStream vc = new FileInputStream(valeurConseil);
        Workbook workbook = new XSSFWorkbook(pc);

        Sheet productSheet = getSheet(workbook, "PRODUIT");
        checkArgument(productSheet != null, "Can't find sheet <PRODUIT> in file " + produitConseil);

        Sheet codeIsinSheet = getSheet(workbook, "PRODUIT_VALEUR_CDN_COPERNIC");
        checkArgument(codeIsinSheet != null, "Can't find sheet <PRODUIT_VALEUR_CDN_COPERNIC> in file " + produitConseil);

        Sheet supportSheet = getSheet(new XSSFWorkbook(vc), "VALEUR");
        checkArgument(supportSheet != null, "Can't find sheet <VALEUR> in file " + valeurConseil);

        boolean first = true;
        for (Row row : productSheet) {
            if (first) {
                first = false;
                continue;
            }
            FamilyProduct familyProduct = new FamilyProduct(cellString(row, 3), cellString(row, 1));
            codeMap.put(familyProduct, cellInt(row, 0));
        }

        first = true;
        for (Row row : codeIsinSheet) {
            if (first) {
                first = false;
                continue;
            }
            int code = cellInt(row, 0);
            String isin = cellString(row, 1);

            Set<String> curIsin = codeIsinMap.get(code);
            if (curIsin == null) {
                curIsin = new HashSet<>();
                codeIsinMap.put(code, curIsin);
            }
            curIsin.add(isin);
        }

        Map<String, Support> libele2SupportMap = new HashMap<>();
        Map<String, Integer> libele2CountMap = new HashMap<>();
        first = true;
        for (Row row : supportSheet) {
            if (first) {
                first = false;
                continue;
            }
            String isin = cellString(row, 0);
            String libele = cellString(row, 2);
            String longLibele = cellString(row, 3);
            int note = cellInt(row, 5);
            if (!isNullOrEmpty(isin) && (!isNullOrEmpty(libele) || !isNullOrEmpty(longLibele))) {
                Support support = new Support(isin, libele, longLibele, note);
                supportMap.put(isin, support);

                if (!isNullOrEmpty(libele) && libele.equals(longLibele)) longLibele = null;
                for (String name : Arrays.asList(libele, longLibele)) {
                    if (isNullOrEmpty(name)) continue;
                    libele2SupportMap.put(name, support);
                    Integer curCount = libele2CountMap.get(name);
                    int c = curCount == null ? 1 : curCount + 1;
                    libele2CountMap.put(name, c);
                }
            }
        }
        pc.close();
        vc.close();

        libele2CountMap.entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .forEach(e -> {
                oneSupportMap.put(e.getKey(), libele2SupportMap.get(e.getKey()).note);
            });
    }

    private Sheet getSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet != null) return sheet;

        return IntStream.range(0, workbook.getNumberOfSheets())
            .mapToObj(workbook::getSheetAt)
            .map(Sheet::getSheetName)
            .map(String::trim)
            .filter(sheetName::equals)
            .map(workbook::getSheet)
            .findFirst()
            .get();

    }

    private String cellString(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) {
            return null;
        }
        return cell.getStringCellValue().trim();
    }

    private int cellInt(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return Integer.parseInt(cell.getStringCellValue().trim());
        }
        return (int) row.getCell(col).getNumericCellValue();
    }

    private void process(String output) throws IOException {
        InputStream inp = new FileInputStream(output);
        Workbook wb = new XSSFWorkbook(inp);
        Sheet sheet = wb.getSheetAt(0);
        checkArgument(sheet != null, "No Sheet found at index 0 in file " + output);

        int rowIndex = 0;
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row curRow = iterator.next();
            String family = cellString(curRow, 0);
            checkArgument(family != null, "No Family found in row " + rowIndex + " in file " + output);

            String product = cellString(curRow, 1);
            checkArgument(product != null, "No Produit found in row " + rowIndex + " in file " + output);

            String support = cellString(curRow, 2);
            checkArgument(support != null, "No support found in row " + rowIndex + " in file " + output);

            Cell cell = curRow.getCell(3) == null ? curRow.createCell(3) : curRow.getCell(3);
            String result = getNote(family, product, support);
            cell.setCellValue(result);
            rowIndex ++;
        }
        inp.close();

        FileOutputStream fileOut = new FileOutputStream(output);
        wb.write(fileOut);
        fileOut.close();
    }

    private String getNote(String family, String product, String support) {
        if (oneSupportMap.containsKey(support)) {
            return Integer.toString(oneSupportMap.get(support));
        }

        FamilyProduct fp = new FamilyProduct(family, product);
        Integer code = codeMap.get(fp);
        if (code == null) {
            return "Can't find code for " + fp;
        }
        Set<String> isinSet = codeIsinMap.get(code);
        if (isinSet == null || isinSet.isEmpty()) {
            return "No isin for code " + code;
        }

        List<Support> results = isinSet.stream()
            .map(supportMap::get)
            .filter(Objects::nonNull)
            .filter(s -> support.equalsIgnoreCase(s.libele) || support.equalsIgnoreCase(s.longLibele))
            .collect(Collectors.toList());

        if (results.isEmpty()) {
            return "no support found for " + fp;
        } else if (results.size() == 1) {
            return results.get(0).note + "";
        } else {
            return "More support found : " + results;
        }
    }

    private static class Support {
        private String isin;

        private String libele;
        private String longLibele;

        private int note;

        public Support(String isin, String libele, String longLibele, int note) {
            this.isin = isin;
            this.libele = libele;
            this.longLibele = longLibele;
            this.note = note;
        }

        @Override
        public String toString() {
            return "{" +
                "isin='" + isin + '\'' +
                ", libele='" + libele + '\'' +
                ", longLibele='" + longLibele + '\'' +
                ", note=" + note +
                '}';
        }
    }

    private static class FamilyProduct {
        private String family;
        private String product;

        public FamilyProduct(String family, String product) {
            this.family = family;
            this.product = product;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            FamilyProduct that = (FamilyProduct) o;

            if (family != null ? !family.equals(that.family) : that.family != null) {
                return false;
            }
            return product != null ? product.equals(that.product) : that.product == null;
        }

        @Override
        public int hashCode() {
            int result = family != null ? family.hashCode() : 0;
            result = 31 * result + (product != null ? product.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "FamilyProduct{" + family + ','+ product + '}';
        }
    }

}
