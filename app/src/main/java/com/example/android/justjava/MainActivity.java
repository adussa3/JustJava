/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int quantity = 0;
    private static int price = 0;

    /* Why can these these CheckBox variables be listed as static?
    * Don't they call
    * */
    private static CheckBox whippedCreamCheckbox;
    private static CheckBox chocolateCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Checkbox variables
        whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        
        //
        displayQuantity();
        displayPrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayQuantity();

        String orderSummery = createOrderSummary();
        displayMessage(orderSummery);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create summary of order.
     *
     * @return text summary
     */
    private String createOrderSummary() {
        String orderSummary = "Name: [INSERT NAME HERE] \n";
        orderSummary += "Add whipped cream? " + whippedCreamCheckbox.isChecked() + "\n";
        orderSummary += "Add chocolate? " + chocolateCheckbox.isChecked() + "\n";
        //orderSummary += "Add whipped cream? " + hasWhippedCream() + "\n";
        //orderSummary += "Add chocolate? " + hasChocolate() + "\n";
        orderSummary += "Quantity: " + quantity + "\n";
        orderSummary += "Total: $" + price + "\n";
        orderSummary += "Thank you!";
        return orderSummary;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 99) {
            quantity++;
            displayQuantity();
            displayPrice();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
            displayQuantity();
            displayPrice();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {
        price = calculatePrice();
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(message);
    }
}