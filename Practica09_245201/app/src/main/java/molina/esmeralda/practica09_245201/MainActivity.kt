package molina.esmeralda.practica09_245201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val userRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSave: Button = findViewById(R.id.save_button) as Button
        btnSave.setOnClickListener { saveMarkFromForm() }

        userRef.addChildEventListener(object: ChildEventListener {
            override fun onCancelled (databaseError: DatabaseError) {}
            override fun onChildMoved (dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {} override fun onChildRemoved (dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                try {
                    val usuario = dataSnapshot.getValue(User::class.java)
                    if (usuario != null) {
                        writeMark(usuario)
                    } else {
                        println("Error al convertir el usuario")
                    }
                } catch (e: Exception) {
                    println("Error de conversión: ${e.message}")
                }
            }

        })
    }

    private fun saveMarkFromForm() {
        val name: EditText = findViewById(R.id.et_name)
        val lastName: EditText = findViewById(R.id.et_lastName)
        val age: EditText = findViewById(R.id.et_age)

        val usuario = User(
            name.text.toString(),
            lastName.text.toString(),
            age.text.toString()
        )

        userRef.push().setValue(usuario)
    }


    private fun writeMark(mark: User) {
        var listV: TextView=findViewById(R.id.list_textView) as TextView
        val text = listV.text.toString() + mark.toString() + "\n"
        listV.text = text
    }



}