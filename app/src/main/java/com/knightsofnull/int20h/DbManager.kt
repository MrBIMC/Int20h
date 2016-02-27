package com.knightsofnull.int20h

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager

/**
 * Created by mrbimc on 27.02.16.
 */
object DbManager {

    fun tryConnect2(): String {
        var connection: Connection?
        var retVal = "все плохо!"
        try {
            Class.forName("org.postgresql.Driver")
            val url = "jdbc:postgresql://192.168.20.2:5432/kazprom?user=postgres&password=postgres"
            connection = DriverManager.getConnection(url)
            val sql = "SELECT 1"
            val st = connection.createStatement()
            val rs = st.executeQuery(sql)
            while(rs.next()) {
                retVal += rs.getString(1)
            }
            rs.close()
            st.close()
            connection.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d(javaClass.simpleName, "query exited")
        return retVal
    }

    private fun tryConnect(): Connection? {
        try {
            Class.forName("org.postgresql.Driver")
            DriverManager.getConnection("jdbc:postgresql://192.168.20.2:5432/kazprom", "postgres", "postgres")
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    //        async() {
    //            Log.d(localClassName, "trying to connect!")
    //            tryConnect()?.let {
    //                Log.d(localClassName, "connection exists")
    //                uiThread {
    //                    textView.text = "connection successful"
    //                }
    //            } ?: uiThread {
    //                Log.d(localClassName, "connection doesn't exists")
    //                textView.text = "can't find postgre db driver!"
    //            }
    //        }
}