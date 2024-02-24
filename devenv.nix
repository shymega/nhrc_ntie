{ pkgs, ... }:

{
  packages = [ pkgs.git pkgs.flyctl ];
  languages.nix.enable = true;
  languages.java.enable = true;
  languages.javascript.enable = true;
  devcontainer.enable = true;
  dotenv.enable = true;
}
