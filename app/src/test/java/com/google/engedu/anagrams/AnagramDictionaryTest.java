/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import static org.junit.Assert.*;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * Tests for AnagramDictionary
 */

public class AnagramDictionaryTest {

    private AnagramDictionary anagrams;

    @Before
    public void buildDictionary() throws Exception {

        InputStream inputStream = new ByteArrayInputStream((
                "stop\npouts\ntypists\ntypo\ntypography\ntypos\npots\n"
        ).getBytes());

        anagrams = new AnagramDictionary(inputStream);
    }
    @Test
    public void testGetAnagram() {
        assertArrayEquals(
                anagrams.getAnagrams("stop").toArray(),
                new String[]{"stop", "pots"}
        );
    }

    @Test
    public void getAnagramWithOneMoreLetter() {
        assertArrayEquals(
                anagrams.getAnagramsWithOneMoreLetter("typo").toArray(),
                new String[]{"typos"}
        );
    }

    @Test
    public void testSortLetters() {

        assertEquals(AnagramDictionary.sortLetters("a"), "a");
        assertNotEquals(AnagramDictionary.sortLetters("stop"), "stop");
        assertNotEquals(AnagramDictionary.sortLetters("start"), "stop");
        assertEquals(AnagramDictionary.sortLetters("stop"), "opst");
    }

    @Test
    public void testIsAnagram() {
        assertTrue(AnagramDictionary.isAnagram("a", "a"));
        assertTrue(AnagramDictionary.isAnagram("stop", "pots"));
        assertFalse(AnagramDictionary.isAnagram("start", "stop"));
        assertTrue(AnagramDictionary.isAnagram("stop", "opts"));
        assertFalse(AnagramDictionary.isAnagram("stop", "oipto"));
    }

    @Test
    public void testIsGoodWord() {
       // TODO: This may need to be in AndroidTest
    }



}
