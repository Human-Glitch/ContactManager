//KODY BUSS
//5-2-2016
//COURSE PROJECT


package com.example.kody.contactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameTxt, phoneTxt, emailTxt, addressTxt;
    List<Contact> Contacts = new ArrayList<Contact>(); //Array list for Contact tab
    ListView contactListView; //Objec to be carried into array list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETTING OBJECTS TO OBJECTS IN THE APP
        nameTxt = (EditText) findViewById(R.id.txtName);
        phoneTxt = (EditText) findViewById(R.id.txtPhone);
        emailTxt = (EditText) findViewById(R.id.txtEmail);
        addressTxt = (EditText) findViewById(R.id.txtAddress);
        contactListView = (ListView) findViewById(R.id.listView);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        // SETS UP THE TABS FOR EACH ACTIVITY
        tabHost.setup();

        //CONTACT CREATOR TAB
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        //CONTACT LIST TAB
        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        //SETS ONCLICK LISTENER TO MAIN BUTTON
        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view){
              //PULLS INFO FROM TEXT BOXES TO LISTVIEW
              addContact(nameTxt.getText().toString(), phoneTxt.getText().toString(),
                      emailTxt.getText().toString(), addressTxt.getText().toString());
              populateList(); //POPULATES LIST WITH INFORMATION FROM LISTVIEW

              Toast.makeText(getApplicationContext(), "Your contact has been created",
                      Toast.LENGTH_SHORT).show(); //INFORMS ACTION HAS BEEN COMPLETED ON BUTTON PRESS
          }
        });

        //CHECK FOR EMPTY SUBMISSION FIELD AND PREVENT FURTHER BUTTON CLICK
        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            //ONLY NECESSARY COMPONENT FOR WHAT'S NEEDED IN THE APP
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                // button is turned on if text is put in at least name box
                addBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
    }

    //FILLS CONTACT LIST TAB WITH INFORMATION FROM LISTVIEW XML
    private void populateList(){
        ArrayAdapter <Contact> adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
    }

    //PULLS INFORMATION FROM TEXT FIELDS FOR NEW DEFAULT CONSTRUCTOR FOR CONTACT CLASS
    private void addContact(String name, String phone, String email, String address){
        Contacts.add(new Contact(name, phone, email, address));
    }

    //DELEGATES USER INPUT TO CONTACT LIST TAB
    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(MainActivity.this, R.layout.listview_item, Contacts);
        }

        //PULLS INFORMATION FROM THE CONTACT CREATOR TAB AND SETS IT TO THE LIST TAB
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContact = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentContact.getName());

            TextView phone = (TextView) view.findViewById(R.id.phoneNumber);
            phone.setText(currentContact.getPhone());

            TextView email = (TextView) view.findViewById(R.id.emailAddress);
            email.setText(currentContact.getEmail());

            TextView address = (TextView) view.findViewById(R.id.cAddress);
            address.setText(currentContact.getAddress());

            return view;
        }
    }

   // @Override
    //public boolean OnCreateOptionsMenu(Menu menu){
       // getMenuInflater().inflate(R.menu.main, menu);
      //  return true;
    //}
}

