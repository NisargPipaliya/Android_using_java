## CRUD Operations:

1. **Create:**
```java 
insert(String table, String nullColumnHack, ContentValues values)
// Insert a new row into the database.

insertOrThrow(String table, String nullColumnHack, ContentValues values)
 // Insert a new row, or throw an exception if an error occurs.

insertWithOnConflict(String table, String nullColumnHack, ContentValues initialValues, int conflictAlgorithm)
// Insert a new row with a conflict resolution strategy.
```


2. **Read:**
```java 
query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
//Query the database and retrieve rows.
rawQuery(String sql, String[] selectionArgs)
// Perform a raw SQL query and retrieve the result set.
```
3. **Update:**
```java
update(String table, ContentValues values, String whereClause, String[] whereArgs)
//Update existing rows in the database.
```

4. **Delete:**
```java 
delete(String table, String whereClause, String[] whereArgs)
//Delete rows from the database.
```

## Other Important Methods of SQLiteDatabase:

- `execSQL(String sql)`: Execute a single SQL statement.
- `beginTransaction()`: Start a transaction.
- `setTransactionSuccessful()`: Marks a transaction as successful.
- `endTransaction()`: End a transaction.
- `close()`: Close the database.
- `onCreate(SQLiteDatabase db)`: Called when the database is created for the first time.
- `onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)`: Called when the database needs to be upgraded.
- `compileStatement(String sql)`: Compiles an SQL statement into a reusable pre-compiled statement object.
- `delete(String table, String whereClause, String[] whereArgs)`: Convenience method for deleting rows in the database.
- `deleteDatabase(File file)`: Deletes a database including its journal file and other auxiliary files that may have been created by the database engine.
- 

## SQLiteOpenHelper
## SQLiteOpenHelper Methods:


```java
SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
// Constructor to create a new instance of `SQLiteOpenHelper`. It requires the context, database name, optional `CursorFactory`, and the initial database version.
```

```java
onCreate(SQLiteDatabase db)
//Called when the database is created for the first time. You should create your database tables and initial data in this method.
```

```java
onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
Called when the database needs to be upgraded due to a version change. You should handle any necessary data migration or schema changes in this method.
```
    
```java
getWritableDatabase()
//Returns a writable database instance. If the database does not exist, `onCreate` will be called. If the database needs to be upgraded, onUpgrade will be called.
```


```java 
getReadableDatabase()

//Returns a readable database instance. If the database does not exist, `onCreate` will be called. If the database needs to be upgraded, onUpgrade will be called.
```


```java
onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)

//Called when the database needs to be downgraded due to a version change. You should handle any necessary data migration or schema changes in this method.
```

```java
close()
//Closes the database. You should call this method when you are done using the database to free up resources.
```

**Demo Program**

```java
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // CRUD methods

    // Create: Insert a new contact into the database
    public long insertContact(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Read: Query all contacts from the database
    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Update: Update an existing contact in the database
    public int updateContact(int id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected;
    }

    // Delete: Delete a contact from the database
    public int deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected;
    }

    // Other Important Methods

    // Execute a raw SQL query
    public void executeRawQuery(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    // Start a transaction
    public void beginTransaction() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
    }

    // Mark a transaction as successful
    public void setTransactionSuccessful() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.setTransactionSuccessful();
    }

    // End a transaction
    public void endTransaction() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.endTransaction();
    }
}

```