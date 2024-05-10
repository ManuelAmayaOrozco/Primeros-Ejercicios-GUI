package StudentList

interface IStudentManagement {
    fun loadStudents(
        onFailure: (String) -> Unit
    ): List<String>
    fun saveStudents(
        students: List<String>,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )
}