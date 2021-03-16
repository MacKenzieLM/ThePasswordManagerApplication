package test.password.thepasswordmanagerapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm")
        val formattedDate = dateFormat.format(dateTime)
        textClock.text = formattedDate
    }

    fun save(v: View) {

        val sharedPreferences = getSharedPreferences("ls-password", Context.MODE_PRIVATE)

        //looks for cookie file
        val editor = sharedPreferences.edit()


        // getting info from the text boxes
        val desc = findViewById<EditText>(R.id.desc) //copy for each below
        val description = desc.text.toString()

        val box1 = findViewById<EditText>(R.id.box1)
        val num1 = box1.text.toString()
        val box2 = findViewById<EditText>(R.id.box2)
        val num2 = box2.text.toString()
        val box3 = findViewById<EditText>(R.id.box3)
        val num3 = box3.text.toString()
        val box4 = findViewById<EditText>(R.id.box4)
        val num4 = box4.text.toString()

        //convert to a whole password
        val password = "$num1 - $num2 - $num3 - $num4"

        // date and time
        val dateTime =
                SimpleDateFormat("dd/MM/yyyy, HH:mm:ss", Locale.getDefault()).format(Date())

        //create the java object
        val obj = User(description, password, dateTime)

        // convert to Json string
        val jsonObjString = Gson().toJson(obj)

        Snackbar.make(v, jsonObjString, Snackbar.LENGTH_LONG).show()

        //get current number of saved passwords
        var loadedCounter = sharedPreferences.getInt("counter", 0)
         loadedCounter++ //

        // saves a string
        editor.putString("$loadedCounter", jsonObjString)
        editor.putInt("counter", loadedCounter)

        //does the actual saving
        editor.apply()


    }

    private var minuteUpdateReceiver: BroadcastReceiver? = null
    private fun startMinuteUpdater() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        minuteUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                dateTime()
            }
        }
        registerReceiver(minuteUpdateReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        startMinuteUpdater()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(minuteUpdateReceiver)
    }


    fun buttonGen(view: View) {
        val box1 = findViewById<TextView>(R.id.box1)
        val random1 = Random.nextInt(100)
        val formatNumber1 = String.format("%02d", random1)
        box1.text = "$formatNumber1"

        val box2 = findViewById<TextView>(R.id.box2)
        val random2 = Random.nextInt(100)
        val formatNumber2 = String.format("%02d", random2)
        box2.text = "$formatNumber2"

        val box3 = findViewById<TextView>(R.id.box3)
        val random3 = Random.nextInt(100)
        val formatNumber3 = String.format("%02d", random3)
        box3.text = "$formatNumber3"

        val box4 = findViewById<TextView>(R.id.box4)
        val random4 = Random.nextInt(100)
        val formatNumber4 = String.format("%02d", random4)
        box4.text = "$formatNumber4"
    }

    fun dateTime() {

        val dateTime = Calendar.getInstance().time
        val formattedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
                .format(dateTime)
        val dateTimeHeading = findViewById<TextView>(R.id.textClock)
        dateTimeHeading.text = formattedDate

    }

    fun next(view: View) {

        // to navigate to 'View Passwords' page
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}








