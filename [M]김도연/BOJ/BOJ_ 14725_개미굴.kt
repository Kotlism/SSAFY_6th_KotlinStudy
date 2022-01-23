fun main() {
    val N = readLine()!!.toInt()
    val input = Array(N) { readLine()!!.split(" ").toTypedArray() }

    val tree = Node(HashMap())
    for (data in input) {
        var current = tree.child
        for (j in 1..data[0].toInt()) {
            if (!current.containsKey(data[j])) {
                current[data[j]] = Node(HashMap())
            }
            current = current[data[j]]!!.child
        }
    }

    dfs(tree, "")
}

fun dfs(tree: Node, dep: String) {
    for (data in tree.child.keys.sorted()) {
        println("$dep$data")
        if (tree.child[data] != null) {
            dfs(tree.child[data]!!, "--$dep")
        }
    }
}

data class Node(val child: HashMap<String, Node>)