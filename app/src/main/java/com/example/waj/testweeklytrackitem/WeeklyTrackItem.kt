package com.example.waj.testweeklytrackitem

import java.io.Serializable

/**
 * Created by Administrator on 2018/3/6 0006.
 */

class WeeklyTrackItem : Serializable {
    /**
     * occurredDate : 3477757432573
     * weather : 晴
     * temperature : 14-16度
     * windPower : 东南风3-4级
     * description : 今天进度符合要求
     * solution : 晚上加班
     * weeklyAttaches : [{"file":"5438572357289","fileHash":"fdsafdsafasd","name":"a.png","size":324124,"type":"pic"}]
     * delayReasons : [{"id":"42","name":" 天气原因"}]
     */



    var id: String? = null
        get() = field?:""
    var occurredDate: Long = 0
    var weather: String? = null
        get() = field?:""
    var pm: String? = null
        get() = field?:""
    var temperature: String? = null
        get() = field?:""
    var windPower: String? = null
        get() = field?:""
    var description: String? = null
        get() = field?:""
    var solution: String? = null
        get() = field?:""
    var attachments: MutableList<WeeklyAttach> = ArrayList()
    var delayReasons: MutableList<WeeklyDelayReason> = ArrayList()
    var photoTypeId: String? = null
    var photoTypeValue: String? = null
    var expatiation:String? = null
    var taskId: String? = null
    var localOperType:String? = null

    constructor()


    fun delayReasonStr(): String {
        return delayReasons.joinToString(separator = ",  ",transform = {it.name!!})
    }

    override fun hashCode(): Int {
        var result = (occurredDate xor occurredDate.ushr(32)).toInt()
        result = 31 * result + weather!!.hashCode()
        result = 31 * result + pm!!.hashCode()
        result = 31 * result + temperature!!.hashCode()
        result = 31 * result + windPower!!.hashCode()
        result = 31 * result + description!!.hashCode()
        result = 31 * result + solution!!.hashCode()
        result = 31 * result + if (attachments != null) attachments!!.hashCode() else 0
        result = 31 * result + if (delayReasons != null) delayReasons!!.hashCode() else 0
        result = 31 * result + if (expatiation != null) expatiation!!.hashCode() else 0
        return result
    }
}

