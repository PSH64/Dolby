package kr.ac.shingu.dolby

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true) // no 에 값이 없을 때 자동증가된 숫자값을 db에 입력해준다.
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var content: Long = 0
    @ColumnInfo(name = "lon")
    var content2: Long = 0

    constructor(content:Long, content2:Long) {
        this.content = content
        this.content2 = content2
    }
}