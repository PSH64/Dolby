package kr.ac.shingu.dolby
import androidx.room.*

@Dao
interface MarkerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ety: MarkerETY)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(etyArray: Array<MarkerETY>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(ety: MarkerETY)

    @Query("select * from room_memo")
    fun selectAll(): List<MarkerETY>

    @Delete
    fun delete(ety: MarkerETY)
}