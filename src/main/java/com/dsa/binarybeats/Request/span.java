// if (UpdateInductionReq.getQuestions() != null) {
//     String updateType = UpdateInductionReq.getQuestions_Update_type();

//     ObjectMapper objectMapper = new ObjectMapper();

//     // Saving the new questions to the database
//     questionList = UpdateInductionReq.getQuestions().stream().map(questionReq -> {
//         Questions question = new Questions();
//         question.setQuestion(questionReq.getQuestion());

//         List<Options> optionsList = questionReq.getOption().stream().map(optionReq -> {
//             Options option = new Options();
//             option.setAnswer(optionReq.getOption());
//             option.setIsCorrect(optionReq.isCorrect());
//             option.setQuestionId(question);
//             optionsListRef.add(option);
//             return option;
//         }).collect(Collectors.toList());

//         // Converting list of options to JSON string to save in DB
//         String options = listStringConversion.ObjectToJsonString(optionsList);

//         question.setOptions(options);
//         question.setInductionId(induction);
//         return question;
//     }).collect(Collectors.toList());
//     List<Questions> questions = questionRepository.saveAll(questionList);

//     switch (updateType) {

//     case "Add To Existing Questions":

//         String addedQuestions = AddToExistingQuestions(questions,
//                 objectMapper.readValue(induction.getQuestions(), new TypeReference<List<Questions>>() {
//                 }));// new TypeReference<List<Questions>>() {} = converting the existing questions
//                     // of the induction to list of questions
//         induction.setQuestions(addedQuestions);
//         break;

//     case "Change Questionnaire":
//         // Delete the existing questions
//         questionRepository.deleteByInductionId(induction);
//         String newQuestions = ChangeQuestions(questions);
//         induction.setQuestions(newQuestions);
//         break;
//     default:
//         break;
//     }

// }

// // Deleteing the questions from questionnaire
// if (UpdateInductionReq.getQuestions_Update_type() != null
//         && UpdateInductionReq.getQuestions_Update_type().equals("Delete Question")) {
//     List<BigDecimal> questionIdsToRemove = UpdateInductionReq.getQuestion_To_Be_Removed();

//     // Checking if the questions to be deleted exists
//     List<Questions> questionsToRemove = questionRepository.findAllById(questionIdsToRemove);
//     if (questionsToRemove.isEmpty()) {
//         log.info("InductionService.addInduction : Ends");
//         ProblemDetails problem = ProblemDetails.forNotFound();
//         problem.setCause(CommonVariable.ERROR_OCCURRED.getValue());
//         problem.setDetail("Questions not found");
//         throw new InternLinkException(problem);
//     }

//     optionRepository.deleteByQuestionIds(questionIdsToRemove);
//     questionRepository.deleteAllByIdInBatch(questionIdsToRemove);

//     String questions = deleteQuestion(induction);
//     induction.setQuestions(questions);
// }

// // If there is update in questions, save induction and options. (Questions
// // already saved) Else save only induction.
// if (UpdateInductionReq.getQuestions() != null || UpdateInductionReq.getQuestion_To_Be_Removed() != null) {
//     inductionRepository.save(induction);
//     optionRepository.saveAll(optionsListRef);
// } else {

//     inductionRepository.save(induction);
// }
