# API-Portal-De-Bolsistas
API para um portal que automatiza a entrega de conteúdos, notas e feedback dos instrutores para os alunos. Tendo sido desenvolvida durante um programa de bolsas, ela é 
mais especificamente voltada para atender ao modelo utilizado no programa. O funcionamento, em linhas gerais, consiste em: seguindo a metodologia Scrum, a cada sprint, 
são disponibilizados uma série de cursos que devem ser concluídos pelos alunos. Ao final de cada sprint, os alunos realizam uma avaliação técnica e recebem o seu feedback 
(tanto o técnico como o comportamental).

Nesta perspectiva, a API permite ao instrutor (ou admin) realizar as operações do CRUD para as entidades: aluno (estagiário), material de estudo, tema e sprint. Bem como 
fazer os vínculos pertinentes entre essas entidades, por exemplo, vincular o aluno à sprint, o material de estudo ao tema etc. Também permite o cadastro do feedback dado 
ao final de cada sprint, o qual contém: nota técnica, nota comportamental e temas de reforço (caso o instrutor considere que o aluno está deficiente).

Além disso, automatiza o processo de entrega de feedback, visto que, quando o administrador (ou instrutor) cadastra o feedback do aluno, ele é automaticamente enviado 
ao seu e-mail. 

Foi desenvolvida em conjunto por um grupo de quatro bolsistas.
