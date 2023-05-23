data class Transaction(val amount: Double, val category: String, val date: String)

class BudgetTracker {
    private val transactions = mutableListOf<Transaction>()

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun calculateTotalExpenses(): Double {
        return transactions.filter { it.amount < 0 }.sumByDouble { it.amount }
    }

    fun generateExpenseReport(): Map<String, Double> {
        return transactions.groupBy { it.category }
            .mapValues { (_, transactions) -> transactions.sumByDouble { it.amount } }
    }
}

fun main() {
    val budgetTracker = BudgetTracker()

    budgetTracker.addTransaction(Transaction(-100.0, "Food", "2023-05-20"))
    budgetTracker.addTransaction(Transaction(-50.0, "Shopping", "2023-05-21"))
    budgetTracker.addTransaction(Transaction(-200.0, "Rent", "2023-05-22"))

    val totalExpenses = budgetTracker.calculateTotalExpenses()
    println("Total Expenses: $totalExpenses")

    val expenseReport = budgetTracker.generateExpenseReport()
    println("Expense Report:")
    for ((category, amount) in expenseReport) {
        println("$category: $amount")
    }
}
