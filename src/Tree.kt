abstract class Tree<Key : Comparable<Key>, Value> {

    protected var root: Node? = null

    inner class Node(internal val key: Key, internal var value: Value) {
        internal var left: Node? = null
        internal var right: Node? = null
    }

    fun contains(key: Key): Boolean {
        return get(key) != null
    }

    abstract fun get(key: Key): Value?

    abstract fun insert(key: Key, value: Value)

    abstract fun remove(key: Key)

    fun height(): Int {
        return height(root)
    }

    private fun height(x: Node?): Int {
        return if (x == null) -1 else 1 + Math.max(height(x.left), height(x.right))
    }

    fun size(): Int {
        return size(root)
    }

    private fun size(h: Node?): Int {
        return if (h == null) 0
        else 1 + size(h.left) + size(h.right)
    }
}