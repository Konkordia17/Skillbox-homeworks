package com.example.homework18


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import org.threeten.bp.Instant
import kotlin.random.Random

class DatetimeFragment : Fragment(R.layout.fragment_messages) {
    lateinit var sendButton: Button
    lateinit var messageEditText:EditText
    private var selectedMessageInstant: Instant? = null
    private var messages: List<Message> = listOf()

    private var messageAdapter: MessageAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
       messageEditText = requireView().findViewById(R.id.messageEditText)
        sendButton = requireView().findViewById(R.id.sendButton)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        messageAdapter = null
    }

    private fun initList() = with(messageList) {
        messageAdapter = MessageAdapter()
        adapter = messageAdapter
        setHAsFixeSize(true)
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initMessageField(){
        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().takeIf { it.isNotBlank()}
                ?:return@setOnClickListener
                val newMessage = Message(
                    id = Random.nextLong(),
                    text = messageText,
                    createdAt = selectedMessageInstant?:Instant.now()
                )
            messages = messages+ listOf(newMessage)
            messageAdapter?.submitList(messages)
            messageEditText.setText(" ")
            selectedMessageInstant = null
            }
        }
    private fun initTimePicker(){
          

    }
}