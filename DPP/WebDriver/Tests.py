# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re


class AppDynamicsJob(unittest.TestCase):
    def setUp(self):
        # AppDynamics will automatically override this web driver
        # as documented in https://docs.appdynamics.com/display/PRO44/Write+Your+First+Script
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True

    def test_add_and_multi(self):
        driver = self.driver
        driver.get("https://www.desmos.com/scientific")
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[104]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[114]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[42]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[62]").click()
        self.assertEqual("0", driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='Your answers show up on this side.'])[1]/following::span[13]").text)

    def test_element_and_multi(self):
        driver = self.driver
        driver.get("https://www.desmos.com/scientific")
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[104]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='ans'])[1]/following::span[1]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[104]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[77]").click()
        driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[104]").click()
        time.sleep(2)
        self.assertEqual("6", driver.find_element_by_xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='Your answers show up on this side.'])[1]/following::span[12]").text)

    def test_calc_regression(self):
        driver = self.driver
        driver.get("https://www.desmos.com/scientific")
        self.assertEqual(u"$$\n÷", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[35]").text)
        self.assertEqual(u"$$\n×", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[77]").text)
        self.assertEqual(u"$$\n−", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[114]").text)
        self.assertEqual("$$\n+", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='ans'])[1]/following::span[1]").text)
        self.assertEqual(u"$$\na\nb", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[119]").text)
        self.assertEqual("$$\n%", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[82]").text)
        self.assertEqual("$$\nsin", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[87]").text)
        self.assertEqual("$$\ncos", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[91]").text)
        self.assertEqual("$$\ntan", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[95]").text)
        self.assertEqual("$$\nn", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[49]").text)
        self.assertEqual(u"$$\nπ", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='clear all'])[1]/following::span[57]").text)

    def is_element_present(self, how, what):
        try:
            self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e:
            return False
        return True

    def is_alert_present(self):
        try:
            self.driver.switch_to_alert()
        except NoAlertPresentException as e:
            return False
        return True

    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally:
            self.accept_next_alert = True

    def tearDown(self):
        # To know more about the difference between verify and assert,
        # visit https://www.seleniumhq.org/docs/06_test_design_considerations.jsp#validating-results
        self.assertEqual([], self.verificationErrors)


if __name__ == "__main__":
    unittest.main()
