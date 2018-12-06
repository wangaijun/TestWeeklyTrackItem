package com.example.waj.testweeklytrackitem

/**
 * Created by Administrator on 2018/3/6 0006.
 */
open class WeeklyAttach  {
    /**
     * file : 5438572357289
     * fileHash : fdsafdsafasd
     * name : a.png
     * size : 324124
     * type : pic
     */
    var id:String?=null // = IdGenerator.nextIdString()
    var file: String? = null
        get() = field?:""
    var fileHash: String? = null
        get() = field?:""
    var name: String? = null
        get() = field?:""
    var size: Int = 0
    var type: String? = null
        get() = field?:""

    var createDate: Long = 0
    var tempFile: String? = null
        get() = field?:""

    var videoImg: String? = null
        get() = field?:""

    constructor()


    override fun hashCode(): Int {
        var result = file!!.hashCode()
        result += 31 * result + tempFile!!.hashCode()
        return result
    }

//    fun downloadUrl():String{
//        if(tempFile!!.isNotEmpty())
//            return  tempFile!!
//
//        if (file!!.isNotEmpty()){
//            return getFileDownloadUrlWithWorkspaceIdAndFileId(ProjectInfo.getInstance().project.mWorkspaceId, file!!)
//        }
//        return ""
//    }
//
//    private fun getFileDownloadUrlWithWorkspaceIdAndFileId(workspaceId: String, file: String): String {
//        var template = FileInfo.getDownLoadUrl()
//        if (template.isNotEmpty()){
//            template = template.replace("{workspaceid}",workspaceId,true)
//            template = template.replace("{fileid}",file,true)
//            return template
//        }
//        return String.format("https://api.glodon.com/workspace/%s/appdata/id/%s?content",workspaceId,file)
//    }
}
