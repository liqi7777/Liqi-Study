package com.jz.test.redistest.KDTree.kdtree;

// Editor.java : class for adding/removing nodes from KD-Tree
//
// Copyright (C) Michael Lorton and Simon D. Levy 2014
//
// This code is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as 
// published by the Free Software Foundation, either version 3 of the 
// License, or (at your option) any later version.
//
// This code is distributed in the hope that it will be useful,     
// but WITHOUT ANY WARRANTY without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License 
//  along with this code.  If not, see <http://www.gnu.org/licenses/>.
//  You should also have received a copy of the Parrot Parrot AR.Drone 
//  Development License and Parrot AR.Drone copyright notice and disclaimer 
//  and If not, see 
//   <https://projects.ardrone.org/attachments/277/ParrotLicense.txt> 
// and
//   <https://projects.ardrone.org/attachments/278/ParrotCopyrightAndDisclaimer.txt>.


import com.jz.test.redistest.KDTree.kdtree.exception.KeyDuplicateException;

public interface Editor<T> {
    T edit(T current);

    abstract class BaseEditor<T> implements Editor<T> {
        final T val;

        public BaseEditor(T val) {
            this.val = val;
        }

        public abstract T edit(T current);
    }

    class Inserter<T> extends BaseEditor<T> {
        public Inserter(T val) {
            super(val);
        }

        public T edit(T current) {
            if (current == null) {
                return this.val;
            }
            throw new KeyDuplicateException();
        }
    }

    class OptionalInserter<T> extends BaseEditor<T> {
        public OptionalInserter(T val) {
            super(val);
        }

        public T edit(T current) {
            return (current == null) ? this.val : current;
        }
    }

    class Replacer<T> extends BaseEditor<T> {
        public Replacer(T val) {
            super(val);
        }

        public T edit(T current) {
            return this.val;
        }
    }
}
