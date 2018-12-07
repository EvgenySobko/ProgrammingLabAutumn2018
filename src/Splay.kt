class Splay<Key : Comparable<Key>, Value> : Tree<Key, Value>() {

    private fun splay(size: Node?, key: Key): Node? {
        var root: Node = size ?: return null
        val comparison = key.compareTo(root.key)
        when {
            comparison < 0 -> {
                if (root.left == null) {
                    return root
                }
                val comparison = key.compareTo(root.left!!.key)
                if (comparison < 0) {
                    root.left!!.left = splay(root.left!!.left, key)
                    root = rotateRight(root)!!
                } else if (comparison > 0) {
                    root.left!!.right = splay(root.left!!.right, key)
                    if (root.left!!.right != null)
                        root.left = rotateLeft(root.left!!)
                }
                return if (root.left == null) {
                    root
                } else {
                    rotateRight(root)
                }
            }
            comparison > 0 -> {
                if (root.right == null) {
                    return root
                }
                val comparison = key.compareTo(root.right!!.key)
                if (comparison < 0) {
                    root.right!!.left = splay(root.right!!.left, key)
                    if (root.right!!.left != null)
                        root.right = rotateRight(root.right!!)
                } else if (comparison > 0) {
                    root.right!!.right = splay(root.right!!.right, key)
                    root = rotateLeft(root)!!
                }
                return if (root.right == null) {
                    root
                } else {
                    rotateLeft(root)
                }
            }
            else -> return root
        }
    }

    private fun rotateRight(h: Node?): Node? {
        val x = h!!.left
        h.left = x!!.right
        x.right = h
        return x
    }

    private fun rotateLeft(h: Node): Node? {
        val x = h.right
        h.right = x!!.left
        x.left = h
        return x
    }

    override fun get(key: Key): Value? {
        root = splay(root, key)
        val cmp = key.compareTo(root!!.key)
        return if (cmp == 0) {
            root!!.value
        } else {
            null
        }
    }

    fun getRoot(key: Key): Value {
        return root!!.value
    }

    override fun insert(key: Key, value: Value) {
        if (root == null) {
            root = Node(key, value)
            return
        }
        root = splay(root, key)
        val comparison = key.compareTo(root!!.key)
        when {
            comparison < 0 -> {
                var node = Node(key, value)
                node.left = root!!.left
                node.right = root
                root!!.left = null
                root = node
            }
            comparison > 0 -> {
                val node = Node(key, value)
                node.right = root!!.right
                node.left = root
                root!!.right = null
                root = node
            }
            else -> root!!.value = value
        }
    }

    override fun remove(key: Key) {
        if (root == null) return
        root = splay(root, key)
        val comparison = key.compareTo(root!!.key)
        if (comparison == 0) {
            if (root!!.left == null) {
                root = root!!.right
            } else {
                val x = root!!.right
                root = root!!.left
                splay(root, key)
                root!!.right = x
            }
        }
    }
}