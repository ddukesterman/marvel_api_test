package com.trenton.marvel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trenton.marvel.account.AccountUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class LoginValidatorTest {

    @Test
    fun `correct simple email`() {
        assertTrue(AccountUtils.isValidEmail("trentonjduke@gmail.com"))
    }

    @Test
    fun `incorrect missing AT`() {
        assertFalse(AccountUtils.isValidEmail("trentonjdukegmail.com"))
    }

    @Test
    fun `incorrect double AT`() {
        assertFalse(AccountUtils.isValidEmail("trenton@jduke@gmail.com"))
    }

    @Test
    fun `incorrect email start with AT`() {
        assertFalse(AccountUtils.isValidEmail("@trentonjdukegmail.com"))
    }

    @Test
    fun `incorrect email end with AT`() {
        assertFalse(AccountUtils.isValidEmail("trentonjdukegmail.com@"))
    }

    @Test
    fun `incorrect email start with period`() {
        assertFalse(AccountUtils.isValidEmail(".trentonjduke@gmail.com"))
    }
}