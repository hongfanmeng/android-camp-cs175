package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract.TodoNote;
import com.byted.camp.todolist.db.TodoDbHelper;


public class NoteEditActivity extends AppCompatActivity {
    private final static String TAG = "NoteEditActivity";
    private EditText editText;
    private Button addBtn;
    private RadioGroup radioGroup;

    private TodoDbHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        dbHelper = new TodoDbHelper(this);
        database = dbHelper.getWritableDatabase();

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();

        String content = getIntent().getStringExtra("content");
        editText.setText(content);

        Priority priority = (Priority) getIntent().getSerializableExtra("priority");
        Log.d(TAG, String.valueOf(priority.intValue));
        switch (priority) {
            case Low:
                ((AppCompatRadioButton) findViewById(R.id.btn_low)).setChecked(true);
                break;
            case Medium:
                ((AppCompatRadioButton) findViewById(R.id.btn_medium)).setChecked(true);
                break;
            case High:
                ((AppCompatRadioButton) findViewById(R.id.btn_high)).setChecked(true);
                break;
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteEditActivity.this,
                            "Content cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(),
                        getSelectedPriority());
                if (succeed) {
                    Toast.makeText(NoteEditActivity.this,
                            "Note edited", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteEditActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
        database = null;
        dbHelper.close();
        dbHelper = null;
    }

    private boolean saveNote2Database(String content, Priority priority) {
        if (database == null || TextUtils.isEmpty(content)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(TodoNote.COLUMN_CONTENT, content);
//        State state = (State) getIntent().getSerializableExtra("state");
//        values.put(TodoNote.COLUMN_STATE, state.intValue);
//        values.put(TodoNote.COLUMN_DATE, System.currentTimeMillis());
        values.put(TodoNote.COLUMN_PRIORITY, priority.intValue);
        long rowId = getIntent().getLongExtra("id", -1);
        if (rowId != -1) {
            database.update(TodoNote.TABLE_NAME, values,
                    TodoNote._ID + "=?",
                    new String[]{String.valueOf(rowId)});
        }
        return rowId != -1;
    }

    private Priority getSelectedPriority() {
        radioGroup = findViewById(R.id.radio_group);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_high:
                return Priority.High;
            case R.id.btn_medium:
                return Priority.Medium;
            default:
                return Priority.Low;
        }
    }
}
