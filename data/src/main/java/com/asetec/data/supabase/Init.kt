package com.asetec.data.supabase

import io.github.cdimascio.dotenv.Dotenv
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

/** Supabase에 관한 정보 **/
class Init {
    private val dotenv = Dotenv.load()

    val supabaseClient = createSupabaseClient(
        supabaseUrl = dotenv["SUPABASE_URL"],
        supabaseKey = dotenv["SUPABASE_KEY"]
    ) {
        install(Postgrest)
    }
}