// Based on the example code at http://www.seleniumhq.org/docs/03_webdriver.jsp
package edu.drexel.se320;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class SeleniumTest {

    protected final String uiPath = "file:///C:/Users/Finnx/Desktop/SE320/hw5/web/index.html";

    
    // just hit the [+]
    @Test
    public void testShowControls() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement controls1 = driver.findElement(By.id("controls1"));

            assertTrue("Checking that controls is being displayed", 
                       controls1.getAttribute("style").contains("block"));
        } finally {
            driver.quit();
        }
    }
    
    // just hit the [+], then [-]
    @Test
    public void testHideControls() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();
            
            WebElement elt2 = driver.findElement(By.id("controls1minus"));

            // Click on the [+]
            elt2.click();
            

            // Find the form field
            WebElement controls1 = driver.findElement(By.id("controls1"));

            assertTrue("Checking that controls is being displayed", 
                       controls1.getAttribute("style").contains("none"));
        } finally {
            driver.quit();
        }
    }
    
    
    // Example test, but covers the case of simply adding one item to the list
    @Test
    public void testAddOneItem() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Make up a todo
            input.sendKeys("Something to do");

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            addButton.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement li = driver.findElement(By.id("item1"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li.getText().startsWith("Something to do"));
        } finally {
            driver.quit();
        }
    }
    
    //simply adding one item to the list, and deleting it
    @Test
    public void testAddOneItemAndDeleteOne() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Make up a todo
            input.sendKeys("Something to do");

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            addButton.click();
            
            WebElement deleteButton = driver.findElement(By.id("button1"));
            deleteButton.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement ul = driver.findElement(By.id("thelist"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking list is empty", 
                       !ul.toString().contains("<li>"));
        } finally {
            driver.quit();
        }
    }
    
    //simply adding two items to the list
    @Test
    public void testAddTwoItems() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            
            // Make up a todo 1
            input.sendKeys("Something to do");
            addButton.click();
            
            
            //Make up a todo 2
            input.clear();
            input.sendKeys("Something else to do");
            addButton.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement li = driver.findElement(By.id("item1"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li.getText().startsWith("Something to do"));
            
            WebElement li2 = driver.findElement(By.id("item2"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li2.getText().startsWith("Something else to do"));
        } finally {
            driver.quit();
        }
    }
    
    //simply adding two items to the list, and deleting first
    @Test
    public void testAddTwoItemsAndDeleteFirst() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            
            // Make up a todo 1
            input.sendKeys("Something to do");
            addButton.click();
            
            
            //Make up a todo 2
            input.clear();
            input.sendKeys("Something else to do");
            addButton.click();
            
            WebElement deleteFirst = driver.findElement(By.id("button1"));
            deleteFirst.click();

            
            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            
            WebElement li2 = driver.findElement(By.id("item2"));
            
            assertTrue("Checking correct text for added element", 
            		driver.findElements( By.id("item1") ).size() == 0);
            assertTrue("Checking we didnt find the text", li2.getText().contains("Something else to do"));
            
        } finally {
            driver.quit();
        }
    }
    
    //simply adding two items to the list, and deleting second
    @Test
    public void testAddTwoItemsAndDeleteSecond() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            
            // Make up a todo 1
            input.sendKeys("Something to do");
            addButton.click();
            
            
            //Make up a todo 2
            input.clear();
            input.sendKeys("Something else to do");
            addButton.click();
            
            WebElement deleteSecond = driver.findElement(By.id("button2"));
            deleteSecond.click();


            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement li = driver.findElement(By.id("item1"));
            
            assertTrue("Checking we didnt find the text", li.getText().contains("Something to do"));
            
            assertTrue("Checking correct text for added element", 
            		driver.findElements( By.id("item2") ).size() == 0);
        } finally {
            driver.quit();
        }
    }
    
    //simply adding two items to the list, and deleting both
    @Test
    public void testAddTwoItemsAndDeleteBoth() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            
            // Make up a todo 1
            input.sendKeys("Something to do");
            addButton.click();
            
            
            //Make up a todo 2
            input.clear();
            input.sendKeys("Something else to do");
            addButton.click();
            
            WebElement deleteFirst = driver.findElement(By.id("button1"));
            WebElement deleteSecond = driver.findElement(By.id("button2"));
            deleteFirst.click();
            deleteSecond.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement ul = driver.findElement(By.id("thelist"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking list is empty", 
                       !ul.toString().contains("<li>"));
        } finally {
            driver.quit();
        }
    }
    
    
    
    
    
    //these are not in my event flow graph
    
    //simply adding one item to the list, and pressing [-]
    @Test
    public void testAddOneItemAndCollapse() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Make up a todo
            input.sendKeys("Something to do");

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            addButton.click();
            
            
            //Collapse First
            WebElement eltMinus = driver.findElement(By.id("controls1minus"));
            eltMinus.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement li = driver.findElement(By.id("item1"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li.getText().startsWith("Something to do"));
        } finally {
            driver.quit();
        }
    }
    
    //simply adding two items to the list, and pressing [-]
    @Test
    public void testAddTwoItemsAndCollapse() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get(uiPath);
            // Find the + to click to display the form to add a todo
            // Looking up by the id, not the name attribute
            WebElement elt = driver.findElement(By.id("controls1plus"));

            // Click on the [+]
            elt.click();

            // Find the form field
            WebElement input = driver.findElement(By.id("itemtoadd"));

            // Find and click the "Add to list" button
            WebElement addButton = driver.findElement(By.id("addbutton"));
            
            // Make up a todo 1
            input.sendKeys("Something to do");
            addButton.click();
            
            
            //Make up a todo 2
            input.clear();
            input.sendKeys("Something else to do");
            addButton.click();
            
            //Collapse First
            WebElement eltMinus = driver.findElement(By.id("controls1minus"));
            eltMinus.click();

            /* The first element added to the list will have id "item1"
             * Subsequent list items will have IDs item2, item3, etc.
             * Arguably this is too brittle, but rather than forcing you
             * all to become experts on the DOM, you may assume this is done
             * correctly, and/or you're testing this functionality implicitly. */
            WebElement li = driver.findElement(By.id("item1"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li.getText().startsWith("Something to do"));
            
            WebElement li2 = driver.findElement(By.id("item2"));
            // We use startsWith because getText includes the text of the Delete button
            assertTrue("Checking correct text for added element", 
                       li2.getText().startsWith("Something else to do"));
        } finally {
            driver.quit();
        }
    }  

}
