package com.example.contactlistdb

class  CharterInfo{
    companion object {
        var images = arrayOf(R.drawable.hollow,R.drawable.jiji,R.drawable.mato,
        R.drawable.nailsmith,R.drawable.salubra,R.drawable.sly,R.drawable.zote)

        val All: ArrayList<Charters> = ArrayList()
        init {
            All.add(Charters(0,"Hollow Knight", images.random(),"Main Charter",
                "One of many beings called Vessels that the Pale King created with the help of his Soul and emptiness."))

            All.add(Charters(1,"Confessor Jiji", images.random(),"In exchange for a rotten egg, Gigi will summon Shadow to his cave.",
                "The confessor Gigi can be found in Gryazmut Cave, the entrance to which is closed with a simple key."))

            All.add(Charters(2,"Nailmaster Mato", images.random(),"One of the three nail masters",
                "The nail master Mato will teach the character the Hurricane Strike technique."))

            All.add(Charters(3,"Nailsmith", images.random(),
                "The nailsmith will improve the character's nail, asking for a certain amount of Geo for this, and after the first improvement, he will also ask for pieces of pale ore.",
                "After improving the nail to the Net (fourth improvement), the nail smith will come out of his house and ask the protagonist to kill him."))

            All.add(Charters(4,"Salubra", images.random(),"Seller in the game Hollow Knight.",
                "Salubra can be found in the south-eastern part of the Forgotten Crossroads."))

            All.add(Charters(5,"Sly", images.random(),"Trader who can be found at the Forgotten Crossroads after defeating Uterus Zhuzh.",
                ""))

            All.add(Charters(6,"Zot", images.random(),"Self-proclaimed inglorious Knight.",
                "Grumpy and arrogant person, putting himself above the others, despite his extreme incompetence."))

        }
    }
}