package kr.ac.shingu.dolby

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MarkerETY::class], version = 1, exportSchema = false)
abstract class DolbyRoom : RoomDatabase() {
    abstract val markerDAO: MarkerDAO

    companion object {
        private var instance: DolbyRoom? = null

        @Synchronized
        fun getInstance(context: Context?): DolbyRoom {
            if (instance != null || context == null) return instance!!
            try {
                synchronized(DolbyRoom::class) {
                    instance = Room.databaseBuilder(
                        context,
                        DolbyRoom::class.java,
                        "DolbyDB"
                    ).fallbackToDestructiveMigration().build()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Database", "DB 인스턴스 만들기 실패!")
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}