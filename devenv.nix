{ pkgs, ... }:

{
  packages = [ pkgs.git pkgs.flyctl ];
  languages.nix.enable = true;
  languages.java.enable = true;
  languages.javascript.enable = true;
  languages.javascript.npm.enable = true;
  languages.typescript.enable = true;
  devcontainer.enable = true;
  difftastic.enable = true;
  dotenv.enable = true;

  pre-commit.hooks = {
    actionlint.enable = true;
    commitizen.enable = true;
    markdownlint.enable = true;
    nixpkgs-fmt.enable = true;
  };
}
