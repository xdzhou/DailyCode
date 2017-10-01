package com.loic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        System.out.println("Process FINISHED...");
    }

    private void preProcess(String produitConseil, String valeurConseil) throws IOException {
        InputStream pc = new FileInputStream(produitConseil);
        InputStream vc = new FileInputStream(valeurConseil);
        Workbook workbook = new XSSFWorkbook(pc);

        Sheet productSheet = workbook.getSheet("PRODUIT");
        Sheet codeIsinSheet = workbook.getSheet("PRODUIT_VALEUR_CDN_COPERNIC");
        Sheet supportSheet = new XSSFWorkbook(vc).getSheet("VALEUR");

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

        first = true;
        for (Row row : supportSheet) {
            if (first) {
                first = false;
                continue;
            }
            String isin = cellString(row, 0);
            String libele = cellString(row, 3);
            int note = cellInt(row, 5);
            supportMap.put(isin, new Support(isin, libele, note));
        }
        pc.close();
        vc.close();
    }

    private String cellString(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) {
            return null;
        }
        return cell.getStringCellValue();
    }

    private int cellInt(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return Integer.parseInt(cell.getStringCellValue());
        }
        return (int) row.getCell(col).getNumericCellValue();
    }

    private void process(String output) throws IOException {
        InputStream inp = new FileInputStream(output);
        Workbook wb = new XSSFWorkbook(inp);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row curRow = iterator.next();
            String family = cellString(curRow, 0);
            String product = cellString(curRow, 1);
            String support = cellString(curRow, 2);
            Cell cell = curRow.getCell(3) == null ? curRow.createCell(3) : curRow.getCell(3);
            String result = getNote(family, product, support);
            cell.setCellValue(result);
        }
        inp.close();

        FileOutputStream fileOut = new FileOutputStream(output);
        wb.write(fileOut);
        fileOut.close();
    }

    private String getNote(String family, String product, String support) {
        FamilyProduct fp = new FamilyProduct(family, product);
        Integer code = codeMap.get(fp);
        if (code == null) {
            return "Can't find code for " + fp;
        }
        Set<String> isinSet = codeIsinMap.get(code);
        if (isinSet == null || isinSet.isEmpty()) {
            return "No isin for code " + code;
        }
        for (String isin : isinSet) {
            Support st = supportMap.get(isin);
            if (st != null && st.libele.equals(support)) {
                return st.note+"";
            }
        }
        return "no support found for " + fp;
    }

    private static class Support {
        private String isin;
        private String libele;
        private int note;

        public Support(String isin, String libele, int note) {
            this.isin = isin;
            this.libele = libele;
            this.note = note;
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
