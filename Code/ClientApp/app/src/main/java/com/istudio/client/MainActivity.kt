package com.istudio.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.istudio.client.databinding.ActivityMainBinding
import com.istudio.server.ServerInterface

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var serviceInterface: ServerInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Bind the service
        initiateBindingOfService()
        // Set click listeners
        setClickListeners()
    }

    /**
     * <**************> Service Connection **************>
     */
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            // We take concrete implementation of binder and pass to interface and return interface.
            serviceInterface = ServerInterface.Stub.asInterface(iBinder)
            // By here the remote service is connected
            Log.d(TAG, "Remote service is connected")
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Log.d(TAG, "Remote service is dis-connected")
        }
    }

    /**
     * <**************> Initiate binding of service **************>
     */
    private fun initiateBindingOfService() {
        try {
            // Prepare the intent to be fired to the server to invoke the server functionality
            val intent = prepareIntent()
            // Prepare the service connection that is needed to connect client to server
            val serviceConnection = mConnection
            // Bind the service with Intent and service-connection
            bindService(intent, serviceConnection, BIND_AUTO_CREATE)
            Log.d(TAG, "bind service called")
        } catch (ex: Exception) {
            Log.d(TAG, ex.message!!)
        }
    }

    /**
     * <**************> Define the Intent **************>
     * Action: Must be the same one defined in the AIDL server
     * Package: It must be the package name of server
     */
    private fun prepareIntent(): Intent {
        val intentName = "AppServerServiceIntent"
        val serverPackageName = "com.istudio.server"
        val serviceClassName = "com.istudio.server.AppServerService"
        val intent = Intent(intentName)
        intent.setPackage(serverPackageName)
        val componentName = ComponentName(serverPackageName, serviceClassName)
        intent.setComponent(componentName)
        return intent
    }

    /**
     * <**************> Set On-Click Listeners **************>
     */
    private fun setClickListeners() {
        binding?.apply {
            getIntegerButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.integerData.toString()
                    Log.d(TAG, result)
                    getIntegerButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
            getLongButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.longData.toString()
                    Log.d(TAG, result)
                    getLongButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
            getCharButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.characterData.toString()
                    Log.d(TAG, result)
                    getCharButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
            getFloatButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.floatData.toString()
                    Log.d(TAG, result)
                    getFloatButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
            getDoubleButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.floatData.toString()
                    Log.d(TAG, result)
                    getDoubleButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
            getStringButtonId.setOnClickListener { view: View? ->
                try {
                    // Here the client will get the data from the server
                    val result = serviceInterface?.stringData.toString()
                    Log.d(TAG, result)
                    getStringButtonId.text = result
                } catch (e: RemoteException) {
                    Log.d(TAG, e.message!!)
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}