package com.ybu.englishvocab.models

import java.io.Serializable

class Vocab : Serializable {
    var id: Int? = null
    var word: String? = null
    var type: String? = null
    var description: String? = null
    var sentence: String? = null
    var synonyms: String? = null
    var antonyms: String? = null
    var image: ByteArray? = null

    constructor() {}


    constructor(
        id: Int?,
        word: String?,
        type: String?,
        description: String?,
        sentence: String?,
        synonyms: String?,
        antonyms: String?,
        image: ByteArray?
    ) {
        this.id = id
        this.word = word
        this.type = type
        this.description = description
        this.sentence = sentence
        this.synonyms = synonyms
        this.antonyms = antonyms
        this.image = image
    }

    constructor(
        word: String?,
        type: String?,
        description: String?,
        sentence: String?,
        synonyms: String?,
        antonyms: String?,
        image: ByteArray?
    ) {
        this.word = word
        this.type = type
        this.description = description
        this.sentence = sentence
        this.synonyms = synonyms
        this.antonyms = antonyms
        this.image = image
    }

    override fun toString(): String {
        return "Vocab(id=$id, word=$word, type=$type, description=$description, sentence=$sentence, synonyms=$synonyms, antonyms=$antonyms, image=$image)"
    }


}