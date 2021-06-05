package com.ybu.englishvocab.models

import java.io.Serializable

class Question : Serializable {
    var id: Int? = null
    var question: String? = null
    var type: String? = null
    var answer: String? = null
    var option1: String? = null
    var option2: String? = null
    var option3: String? = null
    var option4: String? = null

    constructor() {}

    constructor(
        question: String?,
        type: String?,
        answer: String?,
        option1: String?,
        option2: String?,
        option3: String?,
        option4: String?
    ) {
        this.question = question
        this.type = type
        this.answer = answer
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.option4 = option4
    }

    constructor(
        id: Int?,
        question: String?,
        type: String?,
        answer: String?,
        option1: String?,
        option2: String?,
        option3: String?,
        option4: String?
    ) {
        this.id = id
        this.question = question
        this.type = type
        this.answer = answer
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.option4 = option4
    }

    override fun toString(): String {
        return "Question(id=$id, question=$question, type=$type, answer=$answer, option1=$option1, option2=$option2, option3=$option3, option4=$option4)"
    }


}