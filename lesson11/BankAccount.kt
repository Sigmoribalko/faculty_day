package homework


class BankAccount(val id: String, var balance: Int) {

    fun transfer(to: BankAccount, amount: Int) {
        val first = if (id < to.id) this else to
        val second = if (id < to.id) to else this
        synchronized(first) {
            Thread.sleep(10)
            synchronized(second) {
                if (this.balance >= amount) {
                    this.balance -= amount
                    to.balance += amount
                }
            }
        }
    }
}