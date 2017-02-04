package nyc.c4q.huilin.neighborhoodhub.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.huilin.neighborhoodhub.crier.CrierPost;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by rook on 2/3/17.
 */

class CrierDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "towncrier.db";
    private static final int DATABASE_VERSION = 1;

    private static CrierDatabaseHelper instance;

    public static synchronized CrierDatabaseHelper getInstance(Context context) {

        if (instance == null) {
            instance = new CrierDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private CrierDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(CrierPost.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        cupboard().withDatabase(database).createTables();

        if (database == null){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Use this method for upgrading thedatabase
        cupboard().withDatabase(db).upgradeTables();
    }
}