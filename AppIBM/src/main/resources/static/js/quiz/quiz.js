function quizInit() {

  // Selectors
  const $quizQuestionText = $('.quiz .question .questionText');
  const $quizQuestionOptions = $('.quiz .question .options');
  const $quizProgress = $('.quiz progress');
  const $quizProgressDataCurrent = $('.quiz .progressData .current');
  const $quizProgressDataLimit = $('.quiz .progressData .limit');

  // Data input for Questions and Results
  const questions = [{
    text: 'Which framework is used for building enterprise applications in Java?',
    answers: {
      type: 'multiple',
      options: [{
        text: 'A. Spring Framework',
        weight: 1
      }, {
        text: 'B. ReactJS',
        weight: 0
      }, {
        text: 'C. Vue.js',
        weight: 0
      }]
    },
  }, {
    text: '2. IBM Watson SDK for Java is used to integrate with what type of IBM services?',
    answers: {
      type: 'multiple',
      options: [{
        text: 'A. 1989',
        weight: 0
      }, {
        text: 'B. Artificial intelligence services',
        weight: 1
      }, {
        text: 'C. 2000',
        weight: 0
      }]
    },
  }, {
    text: '3. What is the principle of writing reusable code components in Java?',
    answers: {
      type: 'multiple',
      options: [{
        text: 'A. Cloud storage',
        weight: 0
      }, {
        text: 'B. Artificial intelligence services',
        weight: 0
      }, {
        text: 'C. Blockchain services',
        weight: 1
      }]
    },
  }, {
    text: '4. Which tool is preferred for continuous integration/continuous delivery (CI/CD) in Java projects, often used in IBM tutorials?',
    answers: {
      type: 'multiple',
      options: [{
        text: 'A. Jenkins',
        weight: 1
      }, {
        text: 'B. Travis CI',
        weight: 0
      }, {
        text: 'C. GitLab CI',
        weight: 0
      }]
    },
  }];

  const results = [{
    id: 1,
    text: 'You scored 0',
    minScore: 4
  }, {
    id: 2,
    text: 'You scored 1',
    minScore: 3
  }, {
    id: 3,
    text: 'You scored 2',
    minScore: 2
  }, {
    id: 4,
    text: 'You scored 3',
    minScore: 1
  }, {
    id: 5,
    text: 'You scored 4',
    minScore: 0
  }];

  // QUIZ ENGINE
  function quiz() {
    let currentQuestion = 0; // default starting value
    let currentScore = 0; // default starting value
    $quizProgress.attr("max", questions.length);
    $quizProgressDataLimit.html(questions.length);
    renderQuestion(currentQuestion);

    // RENDER
    function renderQuestion() {
      let questionOptionItem;
      const question = questions[currentQuestion];
      const optionsHtml = [];
      const questionText = question.text;
      const questionOptionText = question.answers.options;
      $quizQuestionText.html(questionText);
      for (let i = 0; i < questionOptionText.length; i++) {
        if (question.answers.type === 'range') {
          questionOptionItem = '<button class="quiz-opt range" value="'
              + questionOptionText[i].weight + '" id="'
              + questionOptionText[i].text + '">' + questionOptionText[i].text
              + '</button>';
        } else {
          questionOptionItem = '<button class="quiz-opt" value="'
              + questionOptionText[i].weight + '" id="'
              + questionOptionText[i].text + '">'
              + questionOptionText[i].text + '</button>';
        }
        optionsHtml.push(questionOptionItem);
      }
      $quizQuestionOptions.html(optionsHtml.join(''));
      $('.quiz button').click(nextQuestion);
    } // END renderQuestion

    // HANDLER
    function nextQuestion() {
      const optionValue = parseInt(this.value);
      if (optionValue === 1) { // only increment score if the selected option is correct
        currentScore += optionValue;
      }
      currentQuestion += 1;
      $quizProgress.attr("value", currentQuestion);
      $quizProgressDataCurrent.html(currentQuestion);
      if (questions.length === currentQuestion) {
        calculateResults();
      } else {
        renderQuestion();
      }
    } // END nextQuestion

    // RESULTS
    function calculateResults() {
      let resultText = ""; // Initialize an empty string to store the result text
      // Find the result corresponding to the user's total score
      for (let i = 0; i < results.length; i++) {
        if (currentScore >= results[i].minScore) {
          resultText = results[i].text;
          break; // Break the loop once the first suitable result is found
        }
      }
      // Display the result text
      $('.quiz .question').html(
          '<p class="questionText">Quiz Complete. Here are the Results:</p><p>'
          + resultText + '</p>');
    }
  } // END quiz engine

  // Init render
  quiz();

} // END quizInit

$(function () {
  quizInit();
});
