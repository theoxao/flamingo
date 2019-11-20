package com.theoxao.dto


class ProfileView {
    var userId: String? = null
    var nickName: String? = null
    var avatarUrl: String? = null
    var news: Long = 0
    var readTotalTime: String? = null
    var readDuration: Int = 0
    var medalCount: Long = 0
    var excerptCount: Long = 0

    constructor()
    constructor(userId: String?, nickName: String?, avatarUrl: String?) {
        this.userId = userId
        this.nickName = nickName
        this.avatarUrl = avatarUrl
    }

    constructor(nickName: String){
        this.nickName = nickName
    }

}
