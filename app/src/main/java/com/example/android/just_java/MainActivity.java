package com.example.android.just_java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 99;
    int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).short();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less then 1 coffee", Toast.LENGHT_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString(); // (80)* Explanation of this line

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure our if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Add $1 if the user wants whipped cream1
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // Add $2 if the user wants chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total price
        return quantity * basePrice;
    }

    /**
     * Creates a more detailed order summary
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user want whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfUnits) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfUnits);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}