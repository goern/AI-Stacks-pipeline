#!/usr/bin/env python
# based on https://github.com/pypa/pipenv/issues/245

from pipenv.project import Project
from pipenv.utils import convert_deps_to_pip

# Create pip-compatible dependency list
packages = Project().parsed_pipfile.get('packages', {})
deps = convert_deps_to_pip(packages, r=False)

with open('requirements.txt', 'w') as f:
    f.write('\n'.join(deps))
