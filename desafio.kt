enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Aluno(val nome: String, val idade: Int, var estaLogado: Boolean) {
	var matricula: Int? = null
    
	fun informacoesAluno() {
        println("- Aluno: $nome\n- Idade: $idade\n- N° de Matricula: $matricula\n")
    }


}

data class ConteudoEducacional(val nome: String, val duracao: Int = 10)

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Aluno>()
    
    private var ordemMatricula: Int = 1
    
    fun matricular(aluno: Aluno) {
        //DONE("Utilize o parâmetro $aluno para simular uma matrícula (usar a lista de $inscritos).")
        if(aluno.estaLogado) {   
            val matriculaGerada = gerarMatricula()
            aluno.matricula = matriculaGerada
            inscritos.add(aluno)
            println("- Aluno(a) ${aluno.nome}, n° de matrícula ${aluno.matricula} foi matriculado com sucesso!\n")
        
        } else {
            println("\n Aluno(a) ${aluno.nome} não está logado!\nFaça login ou cadastre-se para se inscrever nesta formação.")
        }
    }
    
    // Utiliza os valores armazenados na var 'ordemMatricula' p gerar novas matriculas a chamada bem sucedida do método 'matricular'
    private fun gerarMatricula(): Int {
        val matricula = ordemMatricula
        ordemMatricula++
        return matricula
    }
    
    // Usa um loop p mostrar cada aluno matriculado
    fun mostrarInscritos() {
    	println("\nAlunos matriculados:")
    	
        for (aluno in inscritos) {
        	println("- Aluno: ${aluno.nome}\n  Idade: ${aluno.idade}\n  Matrícula: ${aluno.matricula}\n")
    	}
    }
    
    // Usa um loop p mostrar informações da formação
    fun mostrarConteudos() {
        println("\nConteúdos da Formação:")
        for (conteudo in conteudos) {
            println("- Nome do Conteúdo: ${conteudo.nome}\n  Duração: ${conteudo.duracao}h\n")
        }
    }
}

fun main() {
    //Instanciando alunos
    val aluno = Aluno("Iuri Ranieri O. Batista", 30, true)
    val aluno2 = Aluno("Luigi di Santi Migliotto", 19, true)
    val aluno3 = Aluno("Hadassa Minervina Azevedo", 28, false)		// Não será inscrita, pq a verificação feita não permite!
    
    //Instanciando conteúdos
    val conteudo = ConteudoEducacional("Conceitos básicos e introdução ao Kotlin", duracao = 20)
    val conteudo2 = ConteudoEducacional("Funções Kotlin")
    val conteudo3 = ConteudoEducacional("Conceitos avançados em Kotlin", duracao = 40)
    //Instanciando formação
    val formacao = Formacao("<---> KOTLIN EXPERT <--->", Nivel.AVANCADO, listOf(conteudo, conteudo2, conteudo3))

    println("Antes de matriculados:")
 	aluno.informacoesAluno()
	aluno2.informacoesAluno()
    
    println("Depois de matriculados:")
    formacao.matricular(aluno)
    formacao.matricular(aluno2)
  
    formacao.matricular(aluno3)		// Msg de 'não logado' verificada apartir do atributo Boolean 'estaLogado'
    
    println("\nNome da formação:\n${formacao.nome}\nNível: ${formacao.nivel}")
    formacao.mostrarConteudos()
    println(formacao.mostrarInscritos())
    
	//DONE("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //DONE("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}