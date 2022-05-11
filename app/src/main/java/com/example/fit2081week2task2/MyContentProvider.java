package com.example.fit2081week2task2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

     public static final String CONTENT_AUTHORITY = "fit2081.app.Connor";

     public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

     MovieDatabase db;
    @Override
   public boolean onCreate() {
            db = MovieDatabase.getDatabase(getContext());
            return true;
        }

        @Nullable
        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                String[] selectionArgs, String sortOrder) {

            SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
            builder.setTables(MovieDetails.TABLE_NAME);
            String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);

            final Cursor cursor = db
                    .getOpenHelper()
                    .getReadableDatabase()
                    .query(query, selectionArgs);

            return cursor;
        }

        @Nullable
        @Override
        public String getType(@NonNull Uri uri) {
            return null;
        }

        @Nullable
        @Override
        public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

            long rowId = db
                    .getOpenHelper()
                    .getWritableDatabase()
                    .insert(MovieDetails.TABLE_NAME, 0, contentValues);

            return ContentUris.withAppendedId(CONTENT_URI, rowId);
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            int deletionCount;

            deletionCount = db
                    .getOpenHelper()
                    .getWritableDatabase()
                    .delete(MovieDetails.TABLE_NAME, selection, selectionArgs);

            return deletionCount;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            int updateCount;
            updateCount = db
                    .getOpenHelper()
                    .getWritableDatabase()
                    .update(MovieDetails.TABLE_NAME, 0, values, selection, selectionArgs);

            return updateCount;
        }
}