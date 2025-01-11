package com.asetec.data.helper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class GoogleSignInHelper {
    suspend fun googleSignIn(task: Task<GoogleSignInAccount?>): GoogleSignInAccount {
        return task.result ?: throw Exception("구글 로그인 중 오류")
    }
}