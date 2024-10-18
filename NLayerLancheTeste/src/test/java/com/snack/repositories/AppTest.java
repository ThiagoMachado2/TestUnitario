package com.snack.repositories;


import com.snack.App;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    public void testarShowMenu() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        App.showMenu();

        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("1 - New product"));
        assertTrue(output.contains("2 - Update product"));
        assertTrue(output.contains("3 - List products"));
        assertTrue(output.contains("4 - Sell"));
        assertTrue(output.contains("5 - Remove product"));
        assertTrue(output.contains("6 - Exit"));
    }

    @Test
    public void testarGetUserInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        System.setIn(inputStream);

        App.resolveDependencies();

        int userInput = App.getUserInput();

        assertEquals(3, userInput);
    }
}
