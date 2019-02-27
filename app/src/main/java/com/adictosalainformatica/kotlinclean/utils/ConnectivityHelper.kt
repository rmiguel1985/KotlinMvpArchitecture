package com.adictosalainformatica.kotlinclean.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * Check device's network connectivity and speed
 * @author emil http://stackoverflow.com/users/220710/emil
 */
object ConnectivityHelper {

    private lateinit var context: Context

    /**
     * Get the network info
     * @return
     */
    private val networkInfo: NetworkInfo?
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

    /**
     * Check if there is any connectivity
     * @return
     */
    val isConnected: Boolean
        get() {
            val info = ConnectivityHelper.networkInfo
            return info != null && info.isConnected
        }

    /**
     * Check if there is any connectivity to a Wifi network
     * @return
     */
    val isConnectedWifi: Boolean
        get() {
            val info = ConnectivityHelper.networkInfo
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        }

    /**
     * Check if there is any connectivity to a mobile network
     * @return
     */
    val isConnectedMobile: Boolean
        get() {
            val info = ConnectivityHelper.networkInfo
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
        }

    /**
     * Check if there is fast connectivity
     * @return
     */
    val isConnectedFast: Boolean
        get() {
            val info = ConnectivityHelper.networkInfo
            return info != null && info.isConnected && ConnectivityHelper.isConnectionFast(info.type, info.subtype)
        }

    fun setContext(context: Context) {
        ConnectivityHelper.context = context
    }

    /**
     * Check if the connection is fast
     * @param type
     * @param subType
     * @return
     */
    fun isConnectionFast(type: Int, subType: Int): Boolean {
        return if (type == ConnectivityManager.TYPE_WIFI) {
            true
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
                TelephonyManager.NETWORK_TYPE_GPRS -> false // ~ 100 kbps
                TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
                TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
                TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
                TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
                /*
 * Above API level 7, make sure to set android:targetSdkVersion
 * to appropriate level to use these
 */
                TelephonyManager.NETWORK_TYPE_EHRPD // API level 11
                -> true // ~ 1-2 Mbps
                TelephonyManager.NETWORK_TYPE_EVDO_B // API level 9
                -> true // ~ 5 Mbps
                TelephonyManager.NETWORK_TYPE_HSPAP // API level 13
                -> true // ~ 10-20 Mbps
                TelephonyManager.NETWORK_TYPE_IDEN // API level 8
                -> false // ~25 kbps
                TelephonyManager.NETWORK_TYPE_LTE // API level 11
                -> true // ~ 10+ Mbps
                // Unknown
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }

}