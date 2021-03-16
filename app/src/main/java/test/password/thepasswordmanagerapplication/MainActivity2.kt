package test.password.thepasswordmanagerapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main2.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val dateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yy HH:mm")
        val formattedDate = dateFormat.format(dateTime)
        time.text = formattedDate
    }

    fun load(view: View) {

        //check the shared preferences file
        val sharedPreferences = getSharedPreferences("ls-password", Context.MODE_PRIVATE)
        Toast.makeText(this, "Passwords loaded", Toast.LENGTH_SHORT).show()

        //get current number of saved passwords
        var loadedCounter = sharedPreferences.getInt("counter", 0) //"counter"

        val list = userList as LinearLayout
        for (i in 1..loadedCounter) { // 1 or 0

            val loadedUser = sharedPreferences.getString("$i", "") // description has to increment

            //converts from json back to string
            val javaObject = Gson().fromJson(loadedUser, User::class.java)

            // create a text box
            val desc = TextView(this)
            desc.text = javaObject.description


            val passBox = TextView(this)
            passBox.text = javaObject.password


            val datetime = TextView(this)
            datetime.text = javaObject.dateTime
            //   datetime.setTextAppearance(R.style.TextAppearance_AppCompat_Title)

            // add text box to linear layout
            list.addView(desc)
            list.addView(passBox)
            list.addView(datetime)

        }
    }

    fun backButton(v: View) {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}














