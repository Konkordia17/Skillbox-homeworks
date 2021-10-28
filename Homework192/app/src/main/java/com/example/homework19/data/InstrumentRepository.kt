package com.example.homework19.data

import com.example.homework19.model.MusicalInstruments


class InstrumentRepository {
    var musicalInstruments = listOf(
        MusicalInstruments.StringInstruments(
            id = 1,
            name = "Скрипка",
            image = "https://cdn.pixabay.com/photo/2016/09/02/10/01/violin-1638742_960_720.png",
            stringCount = "4"
        ),
        MusicalInstruments.WindInstruments(
            id = 2,
            name = "Труба",
            image = "https://cdn.pixabay.com/photo/2020/03/31/13/33/music-4987649_960_720.jpg",
        ),
        MusicalInstruments.StringInstruments(
            id = 3,
            name = "Арфа",
            image = "https://cdn.pixabay.com/photo/2019/11/04/11/48/harp-4600984_960_720.png",
            stringCount = "19"
        ),
        MusicalInstruments.WindInstruments(
            id = 4,
            name = "Гобой",
            image = "https://cdn.pixabay.com/photo/2012/04/13/00/39/oboe-31356_960_720.png",
        ),
        MusicalInstruments.StringInstruments(
            id = 5,
            name = "Балалайка",
            image = "https://cdn.pixabay.com/photo/2013/07/13/10/06/balalaika-156560_960_720.png",
            stringCount = "3"
        ),
        MusicalInstruments.WindInstruments(
            id = 6,
            name = "Саксофон",
            image = "https://cdn.pixabay.com/photo/2012/04/12/12/30/saxophone-29816_960_720.png",
        ),
    )

    fun deleteInstrument(
        musicalInstruments: List<MusicalInstruments>,
        position: Int
    ): List<MusicalInstruments> {
        return musicalInstruments.filterIndexed { index, _ -> index != position }
    }
}