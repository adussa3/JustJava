/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int quantity = 1;

    /* Why can these these private class variables be listed as static? */
    private static CheckBox whippedCreamCheckbox;
    private static CheckBox chocolateCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the private class variables
        whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        
        // Display the current
        displayQuantity();
        displayPrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayQuantity();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.order_summary_email_subject, getName()));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        int price = 5;

        if (whippedCreamCheckbox.isChecked()) {
            price += 1;
        }

        if (chocolateCheckbox.isChecked()) {
            price += 2;
        }

        return quantity * price;
    }

    /**
     * Create summary of order.
     *
     * @return text summary
     */
    private String createOrderSummary() {
        if (getName().equals("")) {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.enter_name), Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }

        String orderSummary = getResources().getString(R.string.order_summary_name, getName()) + "\n";
        orderSummary += getResources().getString(R.string.order_summary_whipped_cream, whippedCreamCheckbox.isChecked() ? true : false) + "\n";
        orderSummary += getResources().getString(R.string.order_summary_chocolate, chocolateCheckbox.isChecked() ? true : false) + "\n";
        orderSummary += getResources().getString(R.string.order_summary_quantity, quantity) + "\n";
        orderSummary += getResources().getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(calculatePrice())) + "\n";
        orderSummary += getResources().getString(R.string.thank_you);
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
        } else {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.more_than_99), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity();
            displayPrice();
        } else {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.less_than_1), Toast.LENGTH_SHORT);
            toast.show();
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
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(calculatePrice()));
    }

    /**
     *
     * @param view
     */
    public void displayPrice(View view) {
        displayPrice();
    }

    /**
     *
     */
    private String getName() {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString().trim();
        return name;
    }
}