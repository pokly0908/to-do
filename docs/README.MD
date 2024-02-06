# ERD
![TODO ERD.png](image%2FTODOERD.png)
# API

| 기능       | Method | URL                                | REQUEST                                         | RESPONSE                                                                                                                     |
|----------|--------|------------------------------------|-------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| 회원가입     | POST   | /user/signup                       | {"username: "username", "password": "password"} | {"message": "회원가입성공", "httpStatus": "OK"}                                                                                    |
| 로그인      | POST   | /user/login                        | {”username”: “username”,”password”: “password”} | {"success": "로그인 성공","status": 200}                                                                                          |
| 일정 일부 조회 | GET    | /todo/{todoId}                     |                                                 | {"title": "title123","content": "content123","username": "1234","createAt": "2024-02-06T13:50:36.562537","completed": false} |
| 일정 전체 조회 | GET    | /todo                              |                                                 | {"title": "title123","content": "content123","username": "1234","createAt": "2024-02-06T13:50:36.562537","completed": false} |
| 일정 등록    | POST   | /todo/create                       | {”title”: ”title”,”content”: “content”}         | {”title”: ”title”,”content”: “content”}                                                                                      |
| 일정 수정    | PATCH  | /todo/{todoId}                     | {”title”: ”title”,”content”: “content”}         | {"title": "title","content": "1234","username": "1234","createAt": "2024-02-06T13:47:49.483162","completed": false}          |
| 일정 완료    | PATCH  | /todo/{todoId}/complete            |                                                 | {"title": "title","content": "1234","username": "1234","createAt": "2024-02-06T13:47:49.483162","completed": true}           |
| 댓글 조회    |        |                                    |                                                 |                                                                                                                              |
| 댓글 등록    | POST   | /todo/{todoId}/comment             | {”comment”: comment”}                           | {"comment": "반가워요^^"}                                                                                                        |
| 댓글 수정    | PATCH  | /todo/{todoId}/comment/{commentId} | {”comment”: comment”}                           | {"comment": "반가워요^^(라고할뻔)"}                                                                                                  |
| 댓글 삭제    | DELETE | /todo/{todoId}/comment/{commentId} |                                                 | {"message": "삭제가 완료되었습니다.","httpStatus": "OK"}                                                                               |
