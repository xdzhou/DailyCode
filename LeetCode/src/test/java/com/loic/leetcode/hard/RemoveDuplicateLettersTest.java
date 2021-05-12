package com.loic.leetcode.hard;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class RemoveDuplicateLettersTest {

  @Test
  void remove() throws ExecutionException, InterruptedException {
    //System.out.println(RemoveDuplicateLetters.remove("cbacdcbc"));
    //System.out.println(RemoveDuplicateLetters.remove("bcabc"));
    LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
        .maximumSize(10)
        .build(
            new CacheLoader<String, String>() {
              @Override
              public String load(String key) throws Exception {
                System.out.println(">>thread: " + Thread.currentThread().getName());
                return key + "-1";
              }
            });
    graphs.get("hello");
  }
}