package com.example.reeltag.data.repository

import com.example.reeltag.data.dummy.ReelsDummyData
import com.example.reeltag.data.model.Reel

class ReelRepository {

    fun getReel(): Reel {
        return ReelsDummyData.reel
    }

}