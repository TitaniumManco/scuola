from question_model import Question
from data import question_data
from quiz_brain import QuizBrain

question_bank = []

for i in question_data:
    question_bank.append(Question(i["text"], i["answer"]))

x = QuizBrain(question_bank)

while(x.still_has_questions()):
    x.next_question()
    
print(f"\n\nYou have completed the quiz!!!\nYour final score was: {x.score}/{x.question_number}")