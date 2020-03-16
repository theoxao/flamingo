package com.theoxao.documents

import org.bson.types.ObjectId

/**
 * @author theo
 * @date 20-3-4
 */
data class UserInfo(val _id:ObjectId, val userName:String , val password:String)
