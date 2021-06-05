package com.ybu.englishvocab.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Question
import com.ybu.englishvocab.models.Vocab
import java.io.ByteArrayOutputStream

class Functions {
    companion object {
        fun loadInitialVocabs(context: Context): ArrayList<Vocab> {
            val vocabList: ArrayList<Vocab> = ArrayList()
            vocabList.add(
                Vocab(
                    "Take",
                    "Verb",
                    "To carry or move something from one place to another",
                    "Remember to take your coat when you leave.",
                    "get, take, make, catch",
                    "release, drop, avoid, relinquish",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Add",
                    "Verb",
                    "To put something together with something else so as to increase the size, number, amount, etc.",
                    "They are looking at ways to add further value to their products.",
                    "attach, put on, join, connect",
                    "subtract, reduce, remove, decrease",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Boil",
                    "Verb",
                    "When a liquid boils or when you boil it, it is heated to the point where it forms bubbles and turns to steam or vapour",
                    "The water was bubbling and boiling away.",
                    "foam, bubble, sparkle, coddle, heat",
                    "freeze, cool, calm down, wet",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Put",
                    "Verb",
                    "to move something into a particular place or position",
                    "Put the cases down there, please.",
                    "set, lay, put down",
                    "remove, change, pull",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Remove",
                    "Verb",
                    "to take somebody/something away from a place",
                    "Three children were removed from the school for persistent bad behaviour.",
                    "lift, extract, take, eliminate",
                    "insert, put, set",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Wear",
                    "Verb",
                    "to have something on your body as a piece of clothing, a decoration, etc.",
                    "Three children were removed from the school for persistent bad behaviour.",
                    "put on, draw on, dress",
                    "remove, take off, discard",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Release",
                    "Verb",
                    "to let somebody come out of a place where they have been kept or stuck and unable to leave or move",
                    "The hostages were released unharmed.",
                    "put on, draw on, dress",
                    "decontrol, leave, discharge",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Live",
                    "Verb",
                    "to be alive, especially at a particular time",
                    "He's the greatest player who ever lived.",
                    "experience, exist, survive, keep alive",
                    "die, lose, miss",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Give",
                    "Verb",
                    "to hand something to somebody so that they can look at it, use it or keep it for a time",
                    "She gave her ticket to the woman at the check-in desk.",
                    "pay, yield, serve, deliver, offer",
                    "deny, decline, refuse, retain",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Save",
                    "Verb",
                    "keep safe or rescue (someone or something) from harm or danger.",
                    "she saved a boy from drowning",
                    "rescue, deliver, protect, retrieve",
                    "harm, risk, lose, damage, destroy",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Early",
                    "Adverb",
                    "before the usual or expected time.",
                    "I was planning to finish work early today",
                    "first, initial, primary",
                    "late, behind, overtime, after", drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Carefully",
                    "Adverb",
                    "in a way that deliberately avoids harm or errors; cautiously",
                    "They must be carefully handled and stored",
                    "cautiously, attentively, well",
                    "carelessly, inattentively, sloppily",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Always",
                    "Adverb",
                    "at all times; on all occasions.",
                    "The sun always rises in the east.",
                    "every time, all the time, forever, ever",
                    "barely, hardly ever, almost never", drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Very",
                    "Adverb",
                    "in a high degree.",
                    "very much so",
                    "much, many, full",
                    "inappreciably, incosiderably", drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Loudly",
                    "Adverb",
                    "in a way that produces much noise.",
                    "he laughed loudly",
                    "aloud, out, noisily",
                    "quietly, sliently", drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Seriously",
                    "Adverb",
                    "in a solemn or considered manner.",
                    "he doctor looked seriously at him",
                    "heavily, severely, critically, really",
                    "casually, minor", drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Badly",
                    "Adverb",
                    "in an unsatisfactory, inadequate, or unsuccessful way.",
                    "The war was going badly.",
                    "poorly, ill",
                    "well, acceptably, fine", drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "More",
                    "Adverb",
                    "a greater or additional amount or degree of.",
                    "she poured herself more coffee.",
                    "yet, still, over",
                    "less, fewer, lower", drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "incredibly",
                    "Adverb",
                    "to a great degree; extremely or unusually.",
                    "Michele was incredibly brave",
                    "extremely, really, seriously",
                    "normally, usually, rather", drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Quickly",
                    "Adverb",
                    "at a fast speed; rapidly.",
                    "Reg's illness progressed frighteningly quickly",
                    "rapidly, fast, speedily",
                    "slowly, casually", drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Happy",
                    "Adjective",
                    "in a happy way",
                    "Eleanor giggled happily",
                    "fortunately, luckily",
                    "miserably", drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Amazing",
                    "Adjective",
                    "Very surprising, especially in a way that you like or admire",
                    "he amazing thing is, he really believes he'll get away with it.",
                    "surprising, admirable, wonderful",
                    "boring, unremarkable, ordinary, simple",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Boring",
                    "Adjective",
                    "Not interesting; making you feel tired and impatient",
                    "He's such a boring man!",
                    "tedious, dusty, annoying",
                    "interesting, fascinating, impressive",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Beautiful",
                    "Adjective",
                    "Having beauty; giving pleasure to the senses or to the mind",
                    "What a beautiful day!",
                    "nice, lovely, good, pretty, wonderful",
                    "ugly, undesirable",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Brave",
                    "Adjective",
                    "Willing to do things that are difficult, dangerous or painful; not afraid",
                    "I wasn't brave enough to tell her what I thought of her.",
                    "courageous, valiant, spunky",
                    "watchful, careful, shy",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Small",
                    "Adjective",
                    "of a size that is less than normal or usual.",
                    "the room was small and quiet",
                    "little, minor, few",
                    "big",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Cool",
                    "Adjective",
                    "of or at a fairly low temperature.",
                    "it'll be a cool afternoon",
                    "chilly, fresh, cold",
                    "warm, heated, toasty",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Messy",
                    "Adjective",
                    "untidy or dirty",
                    "his messy hair",
                    "dirty, nasty",
                    "tidy, ordered, organised",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "New",
                    "Adjective",
                    "not existing before; made, introduced, or discovered recently or now for the first time",
                    "new crop varieties",
                    "recent, novel, fresh",
                    "old, past, previous",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Interesting",
                    "Adjective",
                    "arousing curiosity or interest; holding or catching the attention.",
                    "an interesting debate",
                    "exciting, curious",
                    "boring, dull, tired",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "The best of both worlds",
                    "Phrase and Idiom",
                    "having lived you can enjoy two different opportunities at the same time.",
                    "By working part-time and looking after her kids two days a week she managed to get the best of both worlds.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Speak of the devil",
                    "Phrase and Idiom",
                    "the person you’re just talking about actually appears at that moment.",
                    "Hi Tom, speak of the devil, I was just telling Sara about your new car.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "See eye to eye",
                    "Phrase and Idiom",
                    "agreeing with someone",
                    "They finally saw eye to eye on the business deal",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "Once in a blue moon",
                    "Phrase and Idiom",
                    "an event that happens infrequently.",
                    "I only go to the cinema once in a blue moon",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "When pigs fly",
                    "Phrase and Idiom",
                    "something that will never happen.",
                    "When pigs fly she’ll tidy up her room",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "To cost an arm and a leg",
                    "Phrase and Idiom",
                    "something is very expensive.",
                    "Fuel these days costs and arm and a leg.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "A piece of cake",
                    "Phrase and Idiom",
                    "something is very easy.",
                    "The English test was a piece of cake",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "Let the cat out of the bag",
                    "Phrase and Idiom",
                    "to accidentally reveal a secret.",
                    "I let the cat out of the bag about their wedding plans.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )
            vocabList.add(
                Vocab(
                    "To kill two birds with one stone",
                    "Phrase and Idiom",
                    "to solve two problems at once.",
                    "By taking my dad on holiday, I killed two birds with one stone. I got to go away but also spend time with him.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )

            vocabList.add(
                Vocab(
                    "To cut corners",
                    "Phrase and Idiom",
                    "to do something badly or cheaply.",
                    "They really cut corners when they built this bathroom; the shower is leaking.",
                    "",
                    "",
                    drawableToBitmap(R.drawable.vocab, context)
                )
            )



            return vocabList
        }

        fun loadInitialQuestions(context: Context): ArrayList<Question> {
            val questionList: ArrayList<Question> = ArrayList()
            questionList.add(
                Question(
                    "Which word means \"To carry or move something from one place to another\"?",
                    "Verb",
                    "Take",
                    "Add",
                    "Boil",
                    "Drink",
                    "Take"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"To put something together with something else so as to increase the size, number, amount, etc.\"?",
                    "Verb",
                    "Add",
                    "Reduce",
                    "Decrease",
                    "Add",
                    "Take"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"When a liquid boils or when you boil it, it is heated to the point where it forms bubbles and turns to steam or vapour\"?",
                    "Verb",
                    "Boil",
                    "Cool",
                    "Boil",
                    "Wet",
                    "Reduce"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to move something into a particular place or position\"?",
                    "Verb",
                    "Put",
                    "Hot",
                    "Live",
                    "Reduce",
                    "Put"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to take somebody/something away from a place\"?",
                    "Verb",
                    "Remove",
                    "Wear",
                    "Release",
                    "Remove",
                    "Give"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to have something on your body as a piece of clothing, a decoration, etc.\"?",
                    "Verb",
                    "Wear",
                    "Put",
                    "Wear",
                    "Get",
                    "Add"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to let somebody come out of a place where they have been kept or stuck and unable to leave or move\"?",
                    "Verb",
                    "Release",
                    "Subtract",
                    "Get",
                    "Release",
                    "Cancel"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to be alive, especially at a particular time\"?",
                    "Verb",
                    "Live",
                    "Live",
                    "Miss",
                    "Lose",
                    "Die"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"to hand something to somebody so that they can look at it, use it or keep it for a time\"?",
                    "Verb",
                    "Give",
                    "Deny",
                    "Refuse",
                    "Give",
                    "Retain"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"keep safe or rescue (someone or something) from harm or danger.\"?",
                    "Verb",
                    "Save",
                    "Damage",
                    "Deliver",
                    "Risk",
                    "Save"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"before the usual or expected time.\"?",
                    "Adverb",
                    "Early",
                    "Early",
                    "Late",
                    "Behind",
                    "Overtime"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"in a way that deliberately avoids harm or errors; cautiously.\"?",
                    "Adverb",
                    "Carefully",
                    "Carelessly",
                    "Carefully",
                    "Sloppily",
                    "Behind"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"at all times; on all occasions.\"?",
                    "Adverb",
                    "Always",
                    "Barely",
                    "Never",
                    "Hard",
                    "Always"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"in a high degree.\"?",
                    "Adverb",
                    "Very",
                    "Very",
                    "inappreciably",
                    "incosiderably",
                    "forever"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"in a way that produces much noise.\"?",
                    "Adverb",
                    "Loudly",
                    "Well",
                    "Quietly",
                    "Loudly",
                    "Sliently"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"in a solemn or considered manner\"?",
                    "Adverb",
                    "Seriously",
                    "Minor",
                    "Good",
                    "Casually",
                    "Seriously"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"in an unsatisfactory, inadequate, or unsuccessful way\"?",
                    "Adverb",
                    "Badly",
                    "Well",
                    "Good",
                    "Badly",
                    "Acceptable"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"a greater or additional amount or degree of.\"?",
                    "Adverb",
                    "More",
                    "Badly",
                    "Less",
                    "More",
                    "Fine"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"a greater or additional amount or degree of.\"?",
                    "Adverb",
                    "incredibly",
                    "incredibly",
                    "Normally",
                    "Rather",
                    "Usually"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"at a fast speed; rapidly.\"?",
                    "Adverb",
                    "Quickly",
                    "Slowly",
                    "Casually",
                    "Beautiful",
                    "Quickly"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"in a happy way.\"?",
                    "Adjective",
                    "Happy",
                    "fast",
                    "Happy",
                    "miserably",
                    "opportunity"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"Very surprising, especially in a way that you like or admire.\"?",
                    "Adjective",
                    "Amazing",
                    "boring",
                    "Amazing",
                    "unremarkable",
                    "ordinary"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"Not interesting; making you feel tired and impatient\"?",
                    "Adjective",
                    "Boring",
                    "interesting",
                    "Boring",
                    "impressive",
                    "fascinating"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"Having beauty; giving pleasure to the senses or to the mind\"?",
                    "Adjective",
                    "Beautiful",
                    "ugly",
                    "Beautiful",
                    "impressive",
                    "undesirable"
                )
            )

            questionList.add(
                Question(
                    "Which word means \"Willing to do things that are difficult, dangerous or painful; not afraid\"?",
                    "Adjective",
                    "Brave",
                    "Brave",
                    "shy",
                    "careful",
                    "watchful"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"of a size that is less than normal or usual\"?",
                    "Adjective",
                    "Small",
                    "very",
                    "shy",
                    "Small",
                    "good"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"of or at a fairly low temperature\"?",
                    "Adjective",
                    "Cool",
                    "Cool",
                    "toasty",
                    "heated",
                    "warm"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"untidy or dirty\"?",
                    "Adjective",
                    "Messy",
                    "tidy",
                    "ordered",
                    "Messy",
                    "organised"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"not existing before; made, introduced, or discovered recently or now for the first time\"?",
                    "Adjective",
                    "New",
                    "old",
                    "New",
                    "past",
                    "previous"
                )
            )
            questionList.add(
                Question(
                    "Which word means \"arousing curiosity or interest; holding or catching the attention.\"?",
                    "Adjective",
                    "Interesting",
                    "Interesting",
                    "dull",
                    "tired",
                    "boring"
                )
            )

            return questionList
        }

        fun drawableToBitmap(resource: Int, context: Context): ByteArray {
            val drawable: Drawable = context.resources.getDrawable(resource)
            val bitmap = (drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 25, stream)

            return stream.toByteArray()
        }
    }
}