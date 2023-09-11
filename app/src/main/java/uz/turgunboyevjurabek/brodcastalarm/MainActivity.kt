package uz.turgunboyevjurabek.brodcastalarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import uz.turgunboyevjurabek.brodcastalarm.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnBudilnik.setOnClickListener {
            val timePickerDialog=TimePickerDialog(this,object :TimePickerDialog.OnTimeSetListener{
                @SuppressLint("UnspecifiedImmutableFlag")
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    val alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
                    val pendingIntent=PendingIntent.getBroadcast(
                        this@MainActivity,
                        1,
                        intent,
                        0
                    )
                    val intent=Intent(this@MainActivity,MyReceiver::class.java).let {intent ->
                        PendingIntent.getBroadcast(
                            this@MainActivity,
                            0,
                            intent,
                            0
                        )
                    }

                    val calendar=Calendar.getInstance().apply {
                        timeInMillis=System.currentTimeMillis()
                        set(Calendar.HOUR_OF_DAY,hourOfDay)
                        set(Calendar.MINUTE,minute)
                        set(Calendar.SECOND,0)
                    }
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        intent
                    )

                }
            },12,12,true)
            timePickerDialog.show()
        }

    }


}