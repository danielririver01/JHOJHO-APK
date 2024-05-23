package com.example.jhojho

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConfiguracionBd(context: Context, nombre: String = DATABASE_NAME, factory: SQLiteDatabase.CursorFactory? = null, version: Int = DATABASE_VERSION) :
    SQLiteOpenHelper(context, nombre, factory, version) {

    companion object {
        internal const val DATABASE_NAME = "UserDatabase.db"
        internal const val DATABASE_VERSION = 1
        internal const val TABLE_USUARIOS = "usuarios"
        internal const val COLUMN_ID = "codigo"
        internal const val COLUMN_NOMBRES = "nombres"
        internal const val COLUMN_EMAIL = "email"
        internal const val COLUMN_CONTRASENA = "contrasena"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USUARIOS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NOMBRES TEXT, "
                + "$COLUMN_EMAIL TEXT, "
                + "$COLUMN_CONTRASENA TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    // Función para agregar un nuevo usuario
    fun addUser(user: User) {
        val values = ContentValues().apply {
            put(COLUMN_NOMBRES, user.username)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_CONTRASENA, user.password)
        }

        val db = this.writableDatabase
        db.insert(TABLE_USUARIOS, null, values)
        db.close()
    }

    // Función para verificar si el usuario existe
    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USUARIOS, arrayOf(COLUMN_ID),
            "$COLUMN_EMAIL = ? AND $COLUMN_CONTRASENA = ?",
            arrayOf(email, password), null, null, null
        )
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }
}
