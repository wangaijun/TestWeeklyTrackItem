package com.example.waj.testweeklytrackitem

import java.io.Serializable

/**
 * Created by Administrator on 2018/3/6 0006.
 */
class WeeklyDelayReason : Serializable {
    /**
     * id : 42
     * name : 天气原因
     */

    var delayReasonId: String? = null
        get() = field?:""
    var name: String? = null
        get() = field?:""

    constructor(id: String, name: String):this() {
        delayReasonId = id
        this.name = name
    }

    constructor()

    override fun hashCode(): Int {
        var result =  delayReasonId!!.hashCode()
        result += 31 * result + name!!.hashCode()
        return result
    }
}
